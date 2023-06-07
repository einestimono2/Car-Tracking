package com.hust.cartracking.core.data.network

data class BaseResponse<T>(
	val statusCode: Int,
	val message: String? = null,
	val data: T,
)