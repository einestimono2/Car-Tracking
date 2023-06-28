package com.hust.cartracking.features.home.presentation.car_info.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hust.cartracking.core.util.Resource
import com.hust.cartracking.core.util.extensions.TimeFormat
import com.hust.cartracking.core.util.extensions.format
import com.hust.cartracking.core.util.extensions.today
import com.hust.cartracking.features.home.domain.usecase.GetCarImage
import com.hust.cartracking.features.home.domain.usecase.GetCarInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Calendar
import javax.inject.Inject

/********************
 * @Author: Tiiee
 * @Date: 6/28/2023
 * @Time: 11:08 AM
 ********************/

@HiltViewModel
class CarInfoViewModel @Inject constructor(
	private val getCarImage: GetCarImage,
	private val getCarInfo: GetCarInfo,
) : ViewModel() {
	
	private val _state = mutableStateOf(CarInfoState())
	val state: State<CarInfoState> = _state
	
	fun onTriggerEvent(event: CarInfoEvents) {
		when (event) {
			is CarInfoEvents.GetCarInfo -> {
				onGetCarInfo(event.carId)
			}
			
			is CarInfoEvents.GetCarImg -> {
				onGetCarImg(
					event.carId,
					event.fromTime ?: Calendar.getInstance().today.format(TimeFormat.YMD),
					event.toTime ?: Calendar.getInstance().today.format(TimeFormat.YMD),
				)
			}
		}
	}
	
	private fun onGetCarImg(carId: Int, fromTime: String, toTime: String) {
		getCarImage(carId, fromTime, toTime).onEach {
			when (it) {
				is Resource.Loading -> {
					_state.value = _state.value.copy(isLoading = true)
				}
				
				is Resource.Success -> {
					_state.value = _state.value.copy(
						isLoading = false,
						carImgCam1 = it.data?.filter { item -> item.camNo == 1 } ?: emptyList(),
						carImgCam2 = it.data?.filter { item -> item.camNo == 2 } ?: emptyList(),
					)
				}
				
				is Resource.Error -> {
					_state.value = _state.value.copy(
						isLoading = false,
					)
				}
			}
		}.launchIn(viewModelScope)
	}
	
	private fun onGetCarInfo(carId: Int) {
		getCarInfo(carId).onEach {
			when (it) {
				is Resource.Loading -> {
					_state.value = _state.value.copy(isLoading = true)
				}
				
				is Resource.Success -> {
					_state.value = _state.value.copy(
						isLoading = false,
						carInfo = it.data,
					)
				}
				
				is Resource.Error -> {
					_state.value = _state.value.copy(
						isLoading = false,
					)
				}
			}
		}.launchIn(viewModelScope)
	}
	
}