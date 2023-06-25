package com.hust.cartracking.features.home.presentation.monitor_search.viewmodel

import com.hust.cartracking.features.home.domain.model.CarOnline


/********************
 * @Author: Tiiee
 * @Date: 6/19/2023
 * @Time: 4:34 PM
 ********************/

sealed class MonitorSearchEvents {
	
	data class SetListCarOnline(
		val carsOnline: List<CarOnline>
	): MonitorSearchEvents()
	
	data class SearchCarOnline(
		val keyword: String,
		val unit: String,
	): MonitorSearchEvents()
	
	object GetVehicleGroupByUser : MonitorSearchEvents()
	
}