package com.hust.cartracking.features.home.data.remote.response

import com.hust.cartracking.features.home.domain.model.Warning

data class WarningDTO(
	val carId: Int = 0,
	val description: String = "",
	val driverId: Any? = null,
	val driverName: Any? = null,
	val endLatitude: Double = 0.0,
	val endLongitude: Double = 0.0,
	val endTime: String = "",
	val escortId: Any? = null,
	val escortName: Any? = null,
	val id: Int = 0,
	val ktvAtmName: String = "",
	val licensePlate: String = "",
	val ownerId: Any? = null,
	val ownerName: String = "",
	val reason: Any? = null,
	val sampleCode: Any? = null,
	val sampleDetail: Any? = null,
	val sampleName: Any? = null,
	val scheduleId: Int = 0,
	val scheduleName: String = "",
	val securityName: Any? = null,
	val startLatitude: Double = 0.0,
	val startLongitude: Double = 0.0,
	val startTime: String = "",
	val unitId: Int = 0,
	val unitName: String = "",
	val warningCode: String = "",
	val warningLevel: Int = 0,
	val warningName: String = ""
)

fun WarningDTO.toWarning(): Warning {
	return Warning(
		carId = carId,
		description = description,
		driverId = driverId,
		driverName = driverName,
		endLatitude = endLatitude,
		endLongitude = endLongitude,
		endTime = endTime,
		escortId = escortId,
		escortName = escortName,
		id = id,
		ktvAtmName = ktvAtmName,
		licensePlate = licensePlate,
		ownerId = ownerId,
		ownerName = ownerName,
		reason = reason,
		sampleCode = sampleCode,
		sampleDetail = sampleDetail,
		sampleName = sampleName,
		scheduleId = scheduleId,
		scheduleName = scheduleName,
		securityName = securityName,
		startLatitude = startLatitude,
		startLongitude = startLongitude,
		startTime = startTime,
		unitId = unitId,
		unitName = unitName,
		warningCode = warningCode,
		warningLevel = warningLevel,
		warningName = warningName
	)
}