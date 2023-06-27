package com.hust.cartracking.features.home.presentation.monitor.viewmodel

import com.google.android.gms.location.FusedLocationProviderClient

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
	
	data class GetDeviceLocation(
		val fusedLocationProviderClient: FusedLocationProviderClient
	) : MonitorEvents()
}