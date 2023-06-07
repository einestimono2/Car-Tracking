package com.hust.cartracking.features.auth.presentation.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.hust.cartracking.R
import com.hust.cartracking.core.components.TextFieldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(
	label: String,
	emailState: TextFieldState,
	modifier: Modifier = Modifier,
	imeAction: ImeAction = ImeAction.Next,
	onImeAction: () -> Unit = {},
//	clickOutside: Boolean = false,
//	onClickOutside: () -> Unit = {},
) {
	OutlinedTextField(
		singleLine = true,
		value = emailState.text,
		onValueChange = {
			emailState.text = it
			emailState.enableShowErrors()
		},
		label = {
			Text(
				text = label,
				style = MaterialTheme.typography.bodyMedium,
			)
		},
		modifier = modifier
			.fillMaxWidth()
			.onFocusChanged { focusState ->
				emailState.onFocusChange(focusState.isFocused)
				
				if (!focusState.isFocused) {
					emailState.enableShowErrors()
				}
			},
		leadingIcon = {
			Icon(
				imageVector = Icons.Filled.Email,
				contentDescription = stringResource(id = R.string.icon)
			)
		},
		textStyle = MaterialTheme.typography.bodyMedium,
		isError = emailState.showErrors(),
		// Error Text below
		supportingText = {
			emailState.getError()?.let { error -> TextFieldError(textError = error) }
		},
		keyboardOptions = KeyboardOptions.Default.copy(
			imeAction = imeAction,
			keyboardType = KeyboardType.Email
		),
		keyboardActions = KeyboardActions(
			onDone = {
				onImeAction()
			}
		),
	)
	
	// Unfocused when click outside
//	if (clickOutside) {
//		LocalFocusManager.current.clearFocus()
//		onClickOutside()
//	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(
	label: String,
	passwordState: TextFieldState,
	modifier: Modifier = Modifier,
	imeAction: ImeAction = ImeAction.Done,
	onImeAction: () -> Unit = {},
//	clickOutside: Boolean = false,
//	onClickOutside: () -> Unit = {},
) {
	val showPassword = rememberSaveable { mutableStateOf(false) }
	
	OutlinedTextField(
		singleLine = true,
		value = passwordState.text,
		onValueChange = {
			passwordState.text = it
			passwordState.enableShowErrors()
		},
		modifier = modifier
			.fillMaxWidth()
			.onFocusChanged { focusState ->
				passwordState.onFocusChange(focusState.isFocused)
				if (!focusState.isFocused) {
					passwordState.enableShowErrors()
				}
			},
		textStyle = MaterialTheme.typography.bodyMedium,
		label = {
			Text(
				text = label,
				style = MaterialTheme.typography.bodyMedium,
			)
		},
		leadingIcon = {
			Icon(
				imageVector = Icons.Filled.Password,
				contentDescription = stringResource(id = R.string.icon)
			)
		},
		trailingIcon = {
			if (showPassword.value) {
				IconButton(onClick = { showPassword.value = false }) {
					Icon(
						imageVector = Icons.Filled.Visibility,
						contentDescription = stringResource(id = R.string.hide_password)
					)
				}
			} else {
				IconButton(onClick = { showPassword.value = true }) {
					Icon(
						imageVector = Icons.Filled.VisibilityOff,
						contentDescription = stringResource(id = R.string.show_password)
					)
				}
			}
		},
		visualTransformation = if (showPassword.value) {
			VisualTransformation.None
		} else {
			PasswordVisualTransformation()
		},
		isError = passwordState.showErrors(),
		supportingText = {
			passwordState.getError()?.let { error -> TextFieldError(textError = error) }
		},
		keyboardOptions = KeyboardOptions.Default.copy(
			imeAction = imeAction,
			keyboardType = KeyboardType.Password
		),
		keyboardActions = KeyboardActions(
			onDone = {
				onImeAction()
			}
		),
	)
	
	// Unfocused when click outside
//	if (clickOutside) {
//		LocalFocusManager.current.clearFocus()
//		onClickOutside()
//	}
}

@Composable
fun TextFieldError(textError: String) {
	Text(
		text = textError,
		modifier = Modifier.fillMaxWidth(),
		color = MaterialTheme.colorScheme.error
	)
}