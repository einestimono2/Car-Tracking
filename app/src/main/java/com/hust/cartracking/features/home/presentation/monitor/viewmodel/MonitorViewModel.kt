package com.hust.cartracking.features.home.presentation.monitor.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hust.cartracking.core.util.Resource
import com.hust.cartracking.core.util.UiEvent
import com.hust.cartracking.core.util.UiText
import com.hust.cartracking.core.util.extensions.today
import com.hust.cartracking.features.home.domain.usecase.GetAllCarOnline
import com.hust.cartracking.features.home.domain.usecase.GetAllWarning
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

/********************
 * @Author: Tiiee
 * @Date: 6/15/2023
 * @Time: 9:03 PM
 ********************/

@HiltViewModel
class MonitorViewModel @Inject constructor(
	private val getAllCarOnline: GetAllCarOnline,
	private val getAllWarning: GetAllWarning,
) : ViewModel() {
	
	private val _state = mutableStateOf(MonitorState())
	val state: State<MonitorState> = _state
	
	private val _eventFlow = MutableSharedFlow<UiEvent>()
	val eventFlow = _eventFlow.asSharedFlow()
	
	init {
		updateData()
	}
	
	// Call API every 5s
	private fun updateData() {
		viewModelScope.launch {
			while (isActive) {
				onGetAllCarOnline()
				onGetAllWarning()
				delay(120000) // 60s
			}
		}
	}
	
	fun onTriggerEvent(event: MonitorEvents) {
		when (event) {
			is MonitorEvents.GetAllWarning -> {
				onGetAllWarning(event.today, event.unitId)
			}
			
			is MonitorEvents.GetAllCarOnline -> {
				onGetAllCarOnline()
			}
		}
	}
	
	private fun onGetAllWarning(today: String? = null, unitId: Int? = null) {
		getAllWarning(today ?: Date().today(), unitId ?: -1).onEach {
			when (it) {
				is Resource.Loading -> {
					_state.value = _state.value.copy(isLoading = true)
				}
				
				is Resource.Success -> {
					_state.value = _state.value.copy(
						isLoading = false,
						warnings = it.data ?: kotlin.run {
							_eventFlow.emit(
								UiEvent.ShowSnackbar(UiText.unknownError())
							)
							return@onEach
						}
					)
				}
				
				is Resource.Error -> {
					_state.value = _state.value.copy(isLoading = false)
					_eventFlow.emit(
						UiEvent.ShowSnackbar(it.message ?: UiText.unknownError())
					)
				}
			}
		}.launchIn(viewModelScope)
	}
	
	private fun onGetAllCarOnline() {
		getAllCarOnline().onEach {
			when (it) {
				is Resource.Loading -> {
					_state.value = _state.value.copy(isLoading = true)
				}
				
				is Resource.Success -> {
					_state.value = _state.value.copy(
						isLoading = false,
						cars = it.data ?: kotlin.run {
							_eventFlow.emit(
								UiEvent.ShowSnackbar(UiText.unknownError())
							)
							return@onEach
						}
					)
				}
				
				is Resource.Error -> {
					_state.value = _state.value.copy(isLoading = false)
					_eventFlow.emit(
						UiEvent.ShowSnackbar(it.message ?: UiText.unknownError())
					)
					return@onEach
				}
			}
		}.launchIn(viewModelScope)
	}
	
}