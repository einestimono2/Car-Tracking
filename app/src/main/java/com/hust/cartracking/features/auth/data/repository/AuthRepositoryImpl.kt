package com.hust.cartracking.features.auth.data.repository

import com.google.gson.internal.LinkedTreeMap
import com.hust.cartracking.core.data.local.AppCache
import com.hust.cartracking.core.util.Constants
import com.hust.cartracking.core.util.Constants.ACCESS_TOKEN_EXPIRED_KEY
import com.hust.cartracking.core.util.Resource
import com.hust.cartracking.core.util.UiText
import com.hust.cartracking.core.util.UnitResource
import com.hust.cartracking.core.util.extensions.TimeFormat
import com.hust.cartracking.core.util.extensions.format
import com.hust.cartracking.core.util.extensions.today
import com.hust.cartracking.core.util.extensions.tokenExpired
import com.hust.cartracking.core.util.handleException
import com.hust.cartracking.features.auth.data.remote.AuthAPI
import com.hust.cartracking.features.auth.data.remote.request.LoginRequest
import com.hust.cartracking.features.auth.data.remote.response.AuthDTO
import com.hust.cartracking.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.util.Calendar
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
	private val api: AuthAPI,
	private val appCacheManager: AppCache
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
			
			// Save access token and expired of token (1d - 30m)
			appCacheManager.setValue(Constants.ACCESS_TOKEN_VALUE_KEY, authDTO.accessToken)
			
			val expired = Calendar.getInstance().tokenExpired.format(TimeFormat.YMD_HMS)
			Timber.v("Expired: $expired")
			appCacheManager.setValue(ACCESS_TOKEN_EXPIRED_KEY, expired)
			
			emit(Resource.Success(Unit))
		}
	}.catch { e ->
		emit(handleException(e))
	}
	
	override fun authenticate(): Flow<UnitResource> = flow {
		emit(Resource.Loading())
		
		val now = Calendar.getInstance().today.format(TimeFormat.YMD_HMS)
		val expired = appCacheManager.readValue(ACCESS_TOKEN_EXPIRED_KEY)
		
		Timber.v("Now: $now")
		Timber.v("Expired: $expired")
		
		if (expired == null || now >= expired) {
			Timber.v("Phiên đăng nhập đã hết hạn!")
			
			appCacheManager.clearAllIndex()
			
			emit(Resource.Error(uiText = UiText.StringValue("Phiên đăng nhập đã hết hạn!")))
		} else {
			emit(Resource.Success(Unit))
		}
		
		
	}.catch { e ->
		emit(handleException(e))
	}
}