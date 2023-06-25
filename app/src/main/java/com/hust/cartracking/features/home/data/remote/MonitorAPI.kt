package com.hust.cartracking.features.home.data.remote

import com.hust.cartracking.core.data.network.BaseResponse
import com.hust.cartracking.features.home.data.remote.response.CarOnlineDTO
import com.hust.cartracking.features.home.data.remote.response.VehicleGroupByUserDTO
import com.hust.cartracking.features.home.data.remote.response.WarningDTO
import retrofit2.http.GET
import retrofit2.http.Query

/********************
 * @Author: Tiiee
 * @Date: 5/29/2023
 * @Time: 10:13 PM
 ********************/

interface MonitorAPI {
	@GET("/VehicleGroup/GetVehicleGroupByUser")
	suspend fun getVehicleGroupByUser(): List<VehicleGroupByUserDTO>
	
	@GET("/Monitor/GetAllCarOnline")
	suspend fun getAllCarOnline(@Query("unitId") unitId: Int): List<CarOnlineDTO>
	
	@GET("/Warning/GetAllWarning")
	suspend fun getAllWarning(
		@Query("today") today: String,
		@Query("unitId") unitId: Int,
	): List<WarningDTO>
	
	@GET("/Schedule/GetLstRunningSchedule")
	suspend fun getLstRunningSchedule(
		@Query("today") today: String,
		@Query("unitId") unitId: Int,
	): List<VehicleGroupByUserDTO>
	
	@GET("/Schedule/GetLstUpcomingSchedule")
	suspend fun getLstUpcomingSchedule(
		@Query("today") today: String,
		@Query("unitId") unitId: Int,
	): List<VehicleGroupByUserDTO>
	
	@GET("/Point/GetPointShortDataByGroup")
	suspend fun getPointShortDataByGroup(
		@Query("unitId") unitId: Int,
	): List<VehicleGroupByUserDTO>
	
}