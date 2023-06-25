package com.hust.cartracking.features.home.domain.model

/********************
 * @Author: Tiiee
 * @Date: 6/13/2023
 * @Time: 10:12 PM
 ********************/

data class VehicleGroupByUser(
	val id: Int,
	val name: String,
){
	companion object{
		val vehicleGroupByUsers = listOf(
			VehicleGroupByUser(1, "CN01"),
			VehicleGroupByUser(2, "CN02"),
			VehicleGroupByUser(3, "CN03"),
			VehicleGroupByUser(4, "CN04"),
			VehicleGroupByUser(5, "CN05"),
			VehicleGroupByUser(6, "CN06"),
			VehicleGroupByUser(7, "CN07"),
			VehicleGroupByUser(8, "CN08"),
			VehicleGroupByUser(9, "CN09"),
			VehicleGroupByUser(10, "CN10"),
			VehicleGroupByUser(11, "CN11"),
			VehicleGroupByUser(12, "CN12"),
			VehicleGroupByUser(13, "CN13"),
			VehicleGroupByUser(14, "CN14"),
			VehicleGroupByUser(15, "CN15"),
		)
	}
}
