package com.hust.cartracking.features.home.domain.usecase

import com.hust.cartracking.features.home.domain.repository.MonitorRepository
import javax.inject.Inject

/********************
 * @Author: Tiiee
 * @Date: 6/14/2023
 * @Time: 10:27 PM
 ********************/

class GetAllCarOnline @Inject constructor(
	private val repository: MonitorRepository
) {
	
	operator fun invoke() = repository.getAllCarOnline()
	
}