package com.hust.cartracking.features.home.domain.usecase

import com.hust.cartracking.features.home.domain.repository.MonitorRepository
import javax.inject.Inject

/********************
 * @Author: Tiiee
 * @Date: 6/13/2023
 * @Time: 10:15 PM
 ********************/

class GetVehicleGroupByUser @Inject constructor(
	private val repository: MonitorRepository
) {
	
	operator fun invoke() = repository.getVehicleGroupByUser()
	
}