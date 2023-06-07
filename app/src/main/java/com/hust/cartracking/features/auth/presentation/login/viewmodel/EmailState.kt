package com.hust.cartracking.features.auth.presentation.login.viewmodel

import com.hust.cartracking.core.components.TextFieldState
import com.hust.cartracking.core.components.textFieldStateSaver
import com.hust.cartracking.core.util.Constants.EMAIL_VALIDATION_REGEX
import java.util.regex.Pattern

class EmailState(private val email: String? = null) : TextFieldState(
	validator = ::isEmailValid,
	errorFor = ::emailValidationError
) {
	init {
		email?.let {
			text = it
		}
	}
}

private fun emailValidationError(email: String): String {
	return if (email.isEmpty()) "Địa chỉ email không được trống" else "Địa chỉ email '$email' không hợp lệ!"
}

private fun isEmailValid(email: String): Boolean {
	return Pattern.matches(EMAIL_VALIDATION_REGEX, email)
}

val EmailStateSaver = textFieldStateSaver(EmailState())