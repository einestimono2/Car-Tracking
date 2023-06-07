package com.hust.cartracking.features.auth.presentation.login.viewmodel

import com.hust.cartracking.core.util.UiText
import com.hust.cartracking.features.auth.domain.model.Auth

/********************
 * @Author: Tiiee
 * @Date: 5/29/2023
 * @Time: 10:28 AM
 ********************/
 
 data class LoginState(
	val isLoading: Boolean = false,
//	val authenticated: Boolean = false,
//	val error: UiText? = null,
 )