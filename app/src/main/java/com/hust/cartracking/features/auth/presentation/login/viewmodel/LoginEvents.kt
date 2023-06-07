package com.hust.cartracking.features.auth.presentation.login.viewmodel

/********************
 * @Author: Tiiee
 * @Date: 6/3/2023
 * @Time: 11:44 PM
 ********************/

sealed class LoginEvents{
	
	data class Login(
		val email: String,
		val password: String
	): LoginEvents()
	
}