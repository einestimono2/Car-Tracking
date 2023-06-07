package com.hust.cartracking.features.auth.data.remote

import com.hust.cartracking.core.data.network.BaseResponse
import com.hust.cartracking.features.auth.data.remote.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthAPI {
	
	@POST("api/users/login")
	suspend fun login(
		@Body request: LoginRequest
	): BaseResponse<Any>
	
	@GET("/api/user/authenticate")
	suspend fun authenticate()
	
}