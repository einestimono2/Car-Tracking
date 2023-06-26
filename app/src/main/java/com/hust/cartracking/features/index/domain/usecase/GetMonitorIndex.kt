package com.hust.cartracking.features.index.domain.usecase

import com.hust.cartracking.features.home.domain.repository.MonitorRepository
import com.hust.cartracking.features.index.domain.repository.IndexRepository
import javax.inject.Inject

/********************
 * @Author: Tiiee
 * @Date: 6/25/2023
 * @Time: 5:54 PM
 ********************/

class GetMonitorIndex @Inject constructor(
	private val repository: IndexRepository
) {
	
	operator fun invoke() = repository.getMonitorIndex()
	
}