package com.hust.cartracking.features.home.presentation.monitor_search.viewmodel

import com.hust.cartracking.features.home.domain.model.CarOnline
import com.hust.cartracking.features.home.domain.model.VehicleGroupByUser

/********************
 * @Author: Tiiee
 * @Date: 6/19/2023
 * @Time: 4:34 PM
 ********************/

data class MonitorSearchState (
	val isLoading: Boolean = false,
	val vehicleGroups: List<VehicleGroupByUser> = emptyList(),
	val cars: List<CarOnline> = emptyList(),
	val filterCars: List<CarOnline> = emptyList(),
)