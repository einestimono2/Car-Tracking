package com.hust.cartracking.features.home.presentation.monitor.viewmodel

import com.hust.cartracking.features.home.domain.model.CarOnline
import com.hust.cartracking.features.home.domain.model.Warning

/********************
 * @Author: Tiiee
 * @Date: 6/15/2023
 * @Time: 9:00 PM
 ********************/

data class MonitorState(
	val isLoading: Boolean = false,
	val cars: List<CarOnline> = emptyList(),
	val warnings: List<Warning> = emptyList(),
)