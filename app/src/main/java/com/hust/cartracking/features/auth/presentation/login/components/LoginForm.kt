package com.hust.cartracking.features.auth.presentation.login.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hust.cartracking.BuildConfig
import com.hust.cartracking.R
import com.hust.cartracking.core.util.extensions.heightRatio
import com.hust.cartracking.features.auth.presentation.login.viewmodel.EmailState
import com.hust.cartracking.features.auth.presentation.login.viewmodel.EmailStateSaver
import com.hust.cartracking.features.auth.presentation.login.viewmodel.LoginEvents
import com.hust.cartracking.features.auth.presentation.login.viewmodel.LoginViewModel
import com.hust.cartracking.features.auth.presentation.login.viewmodel.PasswordState

/********************
 * @Author: Tiiee
 * @Date: 6/6/2023
 * @Time: 7:34 PM
 ********************/

@Composable
fun LoginForm(
	currentFocus: FocusManager,
	viewModel: LoginViewModel,
	isLoading: Boolean,
) {
	
	val focusRequester = remember { FocusRequester() }
	
	val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
		mutableStateOf(EmailState(email = BuildConfig.EMAIL))
	}
	val passwordState by remember {
		mutableStateOf(PasswordState(password = BuildConfig.PASSWORD))
	}
	
	val onSubmit = {
		currentFocus.clearFocus()
		
		if (emailState.isValid && passwordState.isValid) {
			viewModel.onTriggerEvent(
				LoginEvents.Login(
					emailState.text,
					passwordState.text
				)
			)
		}
	}
	
	// Email
	Spacer(modifier = Modifier.heightRatio(0.075f))
	Email(
		label = stringResource(id = R.string.email),
		emailState = emailState,
		modifier = Modifier.padding(horizontal = 22.dp),
		onImeAction = { focusRequester.requestFocus() },
	)
	
	// Password
	Spacer(modifier = Modifier.heightRatio(0.015f))
	Password(
		label = stringResource(id = R.string.password),
		passwordState = passwordState,
		modifier = Modifier
			.padding(horizontal = 22.dp)
			.focusRequester(focusRequester),
		onImeAction = { onSubmit() },
	)
	
	// Button
	Spacer(modifier = Modifier.heightRatio(0.075f))
	Button(
		onClick = { onSubmit() },
		shape = RoundedCornerShape(8.dp),
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 22.dp),
		enabled = !isLoading && emailState.isValid && passwordState.isValid
	) {
		Text(
			text = stringResource(id = R.string.sign_in)
		)
	}

}