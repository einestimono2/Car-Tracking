package com.hust.cartracking.features.home.data.remote.response

import com.hust.cartracking.features.home.domain.model.VehicleGroupByUser

data class VehicleGroupByUserDTO(
	val id: Int = 0,
	val name: String = ""
)

fun VehicleGroupByUserDTO.toVehicleGroupByUser(): VehicleGroupByUser {
	return VehicleGroupByUser(
		id = id,
        name = name
	)
}