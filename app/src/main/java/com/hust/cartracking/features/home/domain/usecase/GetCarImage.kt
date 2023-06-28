package com.hust.cartracking.features.home.domain.usecase

import com.hust.cartracking.features.home.domain.repository.MonitorRepository
import javax.inject.Inject

/********************
 * @Author: Tiiee
 * @Date: 6/28/2023
 * @Time: 11:19 AM
 ********************/

class GetCarImage @Inject constructor(
	private val repository: MonitorRepository
) {
	
	operator fun invoke(carId: Int, fromTime: String, toTime: String) =
		repository.getCarImage(carId, fromTime, toTime)
	
}