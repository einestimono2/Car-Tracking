package com.hust.cartracking.features.auth.presentation.login.viewmodel

import com.hust.cartracking.core.components.TextFieldState

class PasswordState(private val password: String? = null) : TextFieldState(
	validator = ::isPasswordValid,
	errorFor = ::passwordValidationError
){
	init {
		password?.let {
			text = it
		}
	}
}

class ConfirmPasswordState(private val passwordState: PasswordState) : TextFieldState() {
	override val isValid
		get() = passwordAndConfirmationValid(passwordState.text, text)
	
	override fun getError(): String? {
		return if (showErrors()) {
			passwordConfirmationError()
		} else {
			null
		}
	}
}

//
private fun isPasswordValid(password: String): Boolean {
	return password.length > 3
}

private fun passwordValidationError(password: String): String {
	return "Mật khẩu không hợp lệ!"
}

private fun passwordAndConfirmationValid(password: String, confirmedPassword: String): Boolean {
	return isPasswordValid(password) && password == confirmedPassword
}

private fun passwordConfirmationError(): String {
	return "Mật khẩu không khớp!"
}