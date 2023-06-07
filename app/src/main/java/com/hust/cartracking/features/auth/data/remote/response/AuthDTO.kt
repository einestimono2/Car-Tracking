package com.hust.cartracking.features.auth.data.remote.response

import com.hust.cartracking.features.auth.domain.model.Auth

data class AuthDTO(
	val accessToken: String = "",
	val scope: String = "",
)

fun AuthDTO.toAuth(): Auth {
	return Auth(
		accessToken = accessToken,
		scope = scope
	)
}