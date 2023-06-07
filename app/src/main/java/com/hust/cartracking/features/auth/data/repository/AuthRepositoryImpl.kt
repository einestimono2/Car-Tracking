package com.hust.cartracking.features.auth.data.repository

import com.google.gson.internal.LinkedTreeMap
import com.hust.cartracking.core.data.local.AppCache
import com.hust.cartracking.core.util.Constants
import com.hust.cartracking.core.util.Resource
import com.hust.cartracking.core.util.UnitResource
import com.hust.cartracking.core.util.handleException
import com.hust.cartracking.features.auth.data.remote.AuthAPI
import com.hust.cartracking.features.auth.data.remote.request.LoginRequest
import com.hust.cartracking.features.auth.data.remote.response.AuthDTO
import com.hust.cartracking.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
	private val api: AuthAPI, private val appCacheManager: AppCache
) : AuthRepository {
	
	override fun login(email: String, password: String): Flow<UnitResource> = flow {
		emit(Resource.Loading())
		
		val response = api.login(LoginRequest(email, password))
		
		if (response.message != null) {
			throw Exception(response.message)
		}
		
		// Kiểm tra loại data trả về
		if (response.data is String) {
			val message: String = response.data
			
			if (message.startsWith("Local:"))
				throw Exception("Mật khẩu không chính xác!")
			else if (message.startsWith("UserScope:"))
				throw Exception("Email không chính xác!")
			else
				throw Exception(message)
		} else {
//			val gson = Gson()
//			val authDTO = gson.fromJson(gson.toJson(response.data), AuthDTO::class.java)
			
			val data = response.data as LinkedTreeMap<*, *>
			val authDTO = AuthDTO(data["accessToken"].toString(), data["scope"].toString())
			
			// Save access token
			appCacheManager.setValue(Constants.ACCESS_TOKEN_KEY, authDTO.accessToken)
			
			emit(Resource.Success(Unit))
		}
	}.catch { e ->
		emit(handleException(e))
	}
	
	override fun authenticate(): Flow<UnitResource> = flow {
		emit(Resource.Loading())
		
		api.authenticate()
		
		emit(Resource.Success(Unit))
		
	}.catch { e ->
		emit(handleException(e))
	}
}