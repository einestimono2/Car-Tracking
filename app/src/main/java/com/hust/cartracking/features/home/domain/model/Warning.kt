package com.hust.cartracking.features.home.domain.model

import com.hust.cartracking.core.util.extensions.convertTime

/********************
 * @Author: Tiiee
 * @Date: 6/14/2023
 * @Time: 10:25 PM
 ********************/

data class Warning(
	val carId: Int,
	val description: String,
	val driverId: Any? = null,
	val driverName: Any? = null,
	val endLatitude: Double,
	val endLongitude: Double,
	val endTime: String,
	val escortId: Any? = null,
	val escortName: Any? = null,
	val id: Int,
	val ktvAtmName: String,
	val licensePlate: String,
	val ownerId: Any? = null,
	val ownerName: String,
	val reason: Any? = null,
	val sampleCode: Any? = null,
	val sampleDetail: Any? = null,
	val sampleName: Any? = null,
	val scheduleId: Int,
	val scheduleName: String,
	val securityName: Any? = null,
	val startLatitude: Double,
	val startLongitude: Double,
	val startTime: String,
	val unitId: Int,
	val unitName: String,
	val warningCode: String,
	val warningLevel: Int,
	val warningName: String
)

val Warning.shortDescription: String
	get(){
		return "BKS: ${this.licensePlate}; Cảnh báo: ${this.warningName}; Tuyến: ${this.escortName}; Bắt đầu: ${this.startTime.convertTime()}; Kết thúc: ${this.endTime.convertTime()}"
	}

