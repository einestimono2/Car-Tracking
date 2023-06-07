package com.hust.cartracking.features.auth.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hust.cartracking.core.components.Loading
import com.hust.cartracking.core.ui.navigation.go
import com.hust.cartracking.core.util.UiEvent
import com.hust.cartracking.core.util.asString
import com.hust.cartracking.core.util.extensions.clickableWithoutRipple
import com.hust.cartracking.features.auth.presentation.login.components.LoginFooter
import com.hust.cartracking.features.auth.presentation.login.components.LoginForm
import com.hust.cartracking.features.auth.presentation.login.components.LoginLogo
import com.hust.cartracking.features.auth.presentation.login.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
	navController: NavHostController,
	snackbarHostState: SnackbarHostState,
	viewModel: LoginViewModel = hiltViewModel(),
) {
	val state = viewModel.state.value
	val context = LocalContext.current
	val currentFocus = LocalFocusManager.current
	
	LaunchedEffect(true) {
		viewModel.eventFlow.collectLatest { event ->
			when (event) {
				is UiEvent.ShowSnackbar -> {
					snackbarHostState.showSnackbar(
						withDismissAction = true,
						message = event.uiText.asString(context)
					)
				}
				
				is UiEvent.Navigate -> {
					snackbarHostState.showSnackbar(
						withDismissAction = true,
						duration = SnackbarDuration.Short,
						message = "Đăng nhập thành công!",
					)
					
					navController.go(event.route)
				}
				
				else -> {}
			}
		}
	}
	
	Box(modifier = Modifier.fillMaxSize()) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.clickableWithoutRipple { currentFocus.clearFocus() },
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Spacer(modifier = Modifier.weight(1f))
			
			LoginLogo()
			
			LoginForm(
				currentFocus = currentFocus,
				viewModel = viewModel,
				isLoading = state.isLoading,
			)
			
			Spacer(modifier = Modifier.weight(3f))
			
			LoginFooter()
		}
		
		Loading(isLoading = state.isLoading)
	}
}
