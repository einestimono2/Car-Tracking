package com.hust.cartracking.features.auth.domain.usecase

import com.hust.cartracking.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(
	private val repository: AuthRepository
) {
	
	operator fun invoke(
		email: String,
		password: String
	) = repository.login(email, password)
	
}