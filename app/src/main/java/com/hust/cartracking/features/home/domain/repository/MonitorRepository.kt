package com.hust.cartracking.features.home.domain.repository

import com.hust.cartracking.core.util.Resource
import com.hust.cartracking.features.home.domain.model.CarOnline
import com.hust.cartracking.features.home.domain.model.PointShortDataByGroup
import com.hust.cartracking.features.home.domain.model.Schedule
import com.hust.cartracking.features.home.domain.model.VehicleGroupByUser
import com.hust.cartracking.features.home.domain.model.Warning
import kotlinx.coroutines.flow.Flow

/********************
 * @Author: Tiiee
 * @Date: 6/13/2023
 * @Time: 10:13 PM
 ********************/

interface MonitorRepository {
	
	fun getVehicleGroupByUser(): Flow<Resource<List<VehicleGroupByUser>>>
	
	fun getAllCarOnline(unitId: Int = -1): Flow<Resource<List<CarOnline>>>
	
	fun getLstRunningSchedule(today: String, unitId: Int = -1): Flow<Resource<List<Schedule>>>
	
	fun getLstUpcomingSchedule(today: String, unitId: Int = -1): Flow<Resource<List<Schedule>>>
	
	fun getAllWarning(today: String, unitId: Int = -1): Flow<Resource<List<Warning>>>
	
	fun getPointShortDataByGroup(unitId: Int = -1): Flow<Resource<List<PointShortDataByGroup>>>
	
}