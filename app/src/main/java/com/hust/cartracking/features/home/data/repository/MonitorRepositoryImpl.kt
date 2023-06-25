package com.hust.cartracking.features.home.data.repository

import com.hust.cartracking.core.util.Resource
import com.hust.cartracking.core.util.handleException
import com.hust.cartracking.features.home.data.remote.MonitorAPI
import com.hust.cartracking.features.home.data.remote.response.toCarOnline
import com.hust.cartracking.features.home.data.remote.response.toVehicleGroupByUser
import com.hust.cartracking.features.home.data.remote.response.toWarning
import com.hust.cartracking.features.home.domain.model.CarOnline
import com.hust.cartracking.features.home.domain.model.PointShortDataByGroup
import com.hust.cartracking.features.home.domain.model.Schedule
import com.hust.cartracking.features.home.domain.model.VehicleGroupByUser
import com.hust.cartracking.features.home.domain.model.Warning
import com.hust.cartracking.features.home.domain.repository.MonitorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

/********************
 * @Author: Tiiee
 * @Date: 6/14/2023
 * @Time: 10:19 PM
 ********************/

class MonitorRepositoryImpl @Inject constructor(
	private val api: MonitorAPI,
) : MonitorRepository {
	
	override fun getVehicleGroupByUser(): Flow<Resource<List<VehicleGroupByUser>>> = flow {
		emit(Resource.Loading())
		
		val response = api.getVehicleGroupByUser()
		
		emit(Resource.Success(response.map { it.toVehicleGroupByUser() }))
		
	}.catch { e ->
		emit(handleException(e))
	}
	
	override fun getAllCarOnline(unitId: Int): Flow<Resource<List<CarOnline>>> = flow {
		emit(Resource.Loading())
		
		val response = api.getAllCarOnline(unitId)
		
		emit(Resource.Success(response.map { it.toCarOnline() }))
		
	}.catch { e ->
		emit(handleException(e))
	}
	
	override fun getLstRunningSchedule(today: String, unitId: Int): Flow<Resource<List<Schedule>>> {
		TODO("Not yet implemented")
	}
	
	override fun getLstUpcomingSchedule(
		today: String,
		unitId: Int
	): Flow<Resource<List<Schedule>>> {
		TODO("Not yet implemented")
	}
	
	override fun getAllWarning(today: String, unitId: Int): Flow<Resource<List<Warning>>> = flow {
		emit(Resource.Loading())
		
		val response = api.getAllWarning(today, unitId)
		
		emit(Resource.Success(response.map { it.toWarning() }))
		
	}.catch { e ->
		emit(handleException(e))
	}
	
	override fun getPointShortDataByGroup(unitId: Int): Flow<Resource<List<PointShortDataByGroup>>> {
		TODO("Not yet implemented")
	}
	
	
}