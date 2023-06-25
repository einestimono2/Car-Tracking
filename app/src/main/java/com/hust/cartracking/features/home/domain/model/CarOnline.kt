package com.hust.cartracking.features.home.domain.model

/********************
 * @Author: Tiiee
 * @Date: 6/14/2023
 * @Time: 10:22 PM
 ********************/
 
data class CarOnline(
	val appVersion: String,
	val bigIconState: String,
	val carId: Int,
	val carStatus: Int,
	val carType: Int,
	val cellId: Int,
	val deviceTime: String,
	val driverId: Int,
	val driverName: String? = null,
	val engineOn: Boolean,
	val firstCamPo: Int,
	val firstCamRotation: Any? = null,
	val gpsLat: Double,
	val gpsLon: Double,
	val gpsVelocity: Int,
	val isGpsLost: Boolean,
	val isJamming: Boolean,
	val isSos: Boolean,
	val isSpoofing: Boolean,
	val lacId: Int,
	val licensePlate: String,
	val networkLat: Double,
	val networkLon: Double,
	val rfidString: String,
	val secondCamRotation: Any? = null,
	val smallIConState: String,
	val stateName: String,
	val strongBoxOpen: Boolean,
	val unitId: Int,
	val unitName: String,
	val userId: Int
)