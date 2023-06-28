package com.hust.cartracking.features.home.domain.usecase

import com.hust.cartracking.features.home.domain.repository.MonitorRepository
import javax.inject.Inject

/********************
 * @Author: Tiiee
 * @Date: 6/28/2023
 * @Time: 11:17 AM
 ********************/

class GetCarInfo @Inject constructor(
	private val repository: MonitorRepository
) {
	
	operator fun invoke(carId: Int) = repository.getCarInfo(carId)
	
}