package com.hust.cartracking.features.auth.domain.repository

import com.hust.cartracking.core.util.UnitResource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
	
	fun login(
		email: String,
		password: String
	): Flow<UnitResource>
	
	fun authenticate(): Flow<UnitResource>
}