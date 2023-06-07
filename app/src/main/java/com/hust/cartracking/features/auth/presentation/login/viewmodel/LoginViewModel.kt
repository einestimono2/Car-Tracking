package com.hust.cartracking.features.auth.presentation.login.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hust.cartracking.core.ui.navigation.Screens
import com.hust.cartracking.core.util.Resource
import com.hust.cartracking.core.util.UiEvent
import com.hust.cartracking.core.util.UiText
import com.hust.cartracking.features.auth.domain.usecase.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
	private val login: Login,
) : ViewModel() {
	
	private val _state = mutableStateOf(LoginState())
	val state: State<LoginState> = _state
	
	private val _eventFlow = MutableSharedFlow<UiEvent>()
	val eventFlow = _eventFlow.asSharedFlow()
	
	fun onTriggerEvent(event: LoginEvents) {
		when (event) {
			is LoginEvents.Login -> {
				onLogin(email = event.email, password = event.password)
			}
		}
	}
	
	private fun onLogin(email: String, password: String) {
		login(email, password).onEach {
			when (it) {
				is Resource.Loading -> {
					_state.value = LoginState(isLoading = true)
				}
				
				is Resource.Success -> {
					_state.value = LoginState(isLoading = false)
					_eventFlow.emit(UiEvent.Navigate(route = Screens.HomeScreen.route))
				}
				
				is Resource.Error -> {
					_state.value = LoginState(isLoading = false)
					_eventFlow.emit(
						UiEvent.ShowSnackbar(it.message ?: UiText.unknownError())
					)
				}
			}
		}.launchIn(viewModelScope)
	}
	
	
}