package com.hust.cartracking.features.home.data.remote.response

import com.hust.cartracking.features.home.domain.model.CarImage


data class CarImgDTO(
	val camImgPath: String = "",
	val camNo: Int = 0,
	val deviceTime: String = ""
)

fun CarImgDTO.toCarImage() = CarImage(
	camImgPath = camImgPath,
	camNo = camNo,
	deviceTime = deviceTime,
)