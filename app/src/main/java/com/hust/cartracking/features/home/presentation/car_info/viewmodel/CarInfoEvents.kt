package com.hust.cartracking.features.home.presentation.car_info.viewmodel


/********************
 * @Author: Tiiee
 * @Date: 6/28/2023
 * @Time: 11:09 AM
 ********************/

sealed class CarInfoEvents {
	
	data class GetCarInfo(
		val carId: Int,
	) : CarInfoEvents()
	
	data class GetCarImg(
		val carId: Int,
		val fromTime: String? = null,
		val toTime: String? = null,
	) : CarInfoEvents()
}