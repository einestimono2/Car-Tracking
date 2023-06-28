package com.hust.cartracking.features.home.domain.model

/********************
 * @Author: Tiiee
 * @Date: 6/28/2023
 * @Time: 11:09 AM
 ********************/

data class CarInfo(
	val carType: Int,
	val id: Int,
	val imei: String,
	val licensePlate: String,
	val unitName: String
)
