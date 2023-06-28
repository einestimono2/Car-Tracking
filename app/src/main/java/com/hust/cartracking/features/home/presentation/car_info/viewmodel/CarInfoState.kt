package com.hust.cartracking.features.home.presentation.car_info.viewmodel

import com.hust.cartracking.features.home.domain.model.CarImage
import com.hust.cartracking.features.home.domain.model.CarInfo

/********************
 * @Author: Tiiee
 * @Date: 6/28/2023
 * @Time: 11:08 AM
 ********************/

data class CarInfoState(
	val carInfo: CarInfo? = null,
	val isLoading: Boolean = false,
	val carImgCam1: List<CarImage> = emptyList(),
	val carImgCam2: List<CarImage> = emptyList(),
)