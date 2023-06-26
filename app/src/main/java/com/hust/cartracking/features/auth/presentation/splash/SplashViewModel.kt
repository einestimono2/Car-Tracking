package com.hust.cartracking.features.auth.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hust.cartracking.core.ui.navigation.Screens
import com.hust.cartracking.core.util.Resource
import com.hust.cartracking.core.util.UiEvent
import com.hust.cartracking.features.auth.domain.usecase.Authenticate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/********************
 * @Author: Tiiee
 * @Date: 6/26/2023
 * @Time: 9:52 PM
 ********************/

@HiltViewModel
class SplashViewModel @Inject constructor(
	private val authenticate: Authenticate
) : ViewModel() {
	
	private val _eventFlow = MutableSharedFlow<UiEvent>()
	val eventFlow = _eventFlow.asSharedFlow()
	
	fun onAuthenticate() {
		authenticate().onEach {
			when (it) {
				is Resource.Success -> {
					_eventFlow.emit(
						UiEvent.Navigate(Screens.HomeScreen.route)
					)
				}
				
				else -> {
					_eventFlow.emit(
						UiEvent.Navigate(Screens.LoginScreen.route)
					)
				}
			}
		}.launchIn(viewModelScope)
	}
	
}