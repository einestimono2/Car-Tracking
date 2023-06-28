package com.hust.cartracking.features.home.data.remote

import com.hust.cartracking.features.home.data.remote.response.CarImgDTO
import com.hust.cartracking.features.home.data.remote.response.CarInfoDTO
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
	
	@GET("/Vehicle/GetCarInfoById")
	suspend fun getCarInfo(
		@Query("carId") carId: Int,
	): CarInfoDTO
	
	@GET("/Vehicle/GetLstCarImgByTime")
	suspend fun getLstCarImgByTime(
		@Query("carId") carId: Int,
		@Query("fromTime", encoded=true) fromTime: String, // encoded = true --> thay thế %25 bằng % khi truyền % vào string
		@Query("toTime", encoded=true) toTime: String,
	): List<CarImgDTO>
	
	// /GetCarInfoById?carId=78
	// /GetLstCarImgByTime?carId=78&fromTime=2023-6-27%200%3A0&toTime=2023-6-27%2023%3A59
}