package com.hust.cartracking.features.auth.domain.usecase

/********************
 * @Author: Tiiee
 * @Date: 5/29/2023
 * @Time: 10:24 AM
 ********************/

data class AuthUC(
	val authenticate: Authenticate,
	val login: Login,
)
