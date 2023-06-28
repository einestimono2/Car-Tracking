package com.hust.cartracking.features.home.data.remote.response

import com.hust.cartracking.features.home.domain.model.CarInfo


data class CarInfoDTO(
	val carType: Int = 0,
	val id: Int = 0,
	val imei: String = "",
	val licensePlate: String = "",
	val unitName: String = ""
)

fun CarInfoDTO.toCarInfo(): CarInfo {
	return CarInfo(
		carType = carType,
		id = id,
		imei = imei,
		licensePlate = licensePlate,
		unitName = unitName
	)
}