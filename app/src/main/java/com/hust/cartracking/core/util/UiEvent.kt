package com.hust.cartracking.core.util

/********************
 * @Author: Tiiee
 * @Date: 6/4/2023
 * @Time: 12:16 PM
 ********************/

sealed class UiEvent {
	data class ShowToast(val uiText: UiText) : UiEvent()
	data class ShowSnackbar(val uiText: UiText) : UiEvent()
	data class Navigate(val route: String) : UiEvent()
	object Authenticated : UiEvent()
	object Unauthenticated : UiEvent()
}