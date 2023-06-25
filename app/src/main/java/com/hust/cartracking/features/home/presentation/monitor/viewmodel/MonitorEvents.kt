package com.hust.cartracking.features.home.presentation.monitor.viewmodel

import com.hust.cartracking.features.home.domain.model.CarOnline

/********************
 * @Author: Tiiee
 * @Date: 6/15/2023
 * @Time: 9:01 PM
 ********************/

sealed class MonitorEvents {
	
	object GetAllCarOnline : MonitorEvents()
	
	data class GetAllWarning(
		val today: String? = null,
		val unitId: Int? = null,
	) : MonitorEvents()
}