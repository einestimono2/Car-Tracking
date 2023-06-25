package com.hust.cartracking.features.home.presentation.monitor_search.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hust.cartracking.core.util.Resource
import com.hust.cartracking.core.util.UiEvent
import com.hust.cartracking.core.util.UiText
import com.hust.cartracking.features.home.domain.model.CarOnline
import com.hust.cartracking.features.home.domain.usecase.GetVehicleGroupByUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/********************
 * @Author: Tiiee
 * @Date: 6/19/2023
 * @Time: 4:34 PM
 ********************/

@HiltViewModel
class MonitorSearchViewModel @Inject constructor(
	private val getVehicleGroupByUser: GetVehicleGroupByUser,
) : ViewModel() {
	
	private val _state = mutableStateOf(MonitorSearchState())
	val state: State<MonitorSearchState> = _state
	
	private val _eventFlow = MutableSharedFlow<UiEvent>()
	val eventFlow = _eventFlow.asSharedFlow()
	
	init {
		onGetVehicleGroupByUser()
	}
	
	fun onTriggerEvent(event: MonitorSearchEvents) {
		when (event) {
			is MonitorSearchEvents.SetListCarOnline -> {
				onSetListCarOnline(carsOnline = event.carsOnline)
			}
			
			is MonitorSearchEvents.GetVehicleGroupByUser -> {
				onGetVehicleGroupByUser()
			}
			
			is MonitorSearchEvents.SearchCarOnline -> {
				onSearchCarOnline(keyword = event.keyword, unit = event.unit)
			}
			
		}
	}
	
	private fun onGetVehicleGroupByUser() {
		getVehicleGroupByUser().onEach {
			when (it) {
				is Resource.Loading -> {
					_state.value = _state.value.copy(isLoading = true)
				}
				
				is Resource.Success -> {
					_state.value = _state.value.copy(
						isLoading = false,
						vehicleGroups = it.data ?: kotlin.run {
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
	
	private fun onSetListCarOnline(carsOnline: List<CarOnline>) {
		_state.value = _state.value.copy(
			cars = carsOnline,
			filterCars = carsOnline,
		)
	}
	
	private fun onSearchCarOnline(keyword: String, unit: String) {
		_state.value = _state.value.copy(
			filterCars = _state.value.cars.filter {
				it.licensePlate.contains(keyword, true) && (
					if (unit == "Tất cả") true else it.unitName == unit
					)
			}
		)
	}
	
}