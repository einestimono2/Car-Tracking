package com.hust.cartracking.features.home.domain.usecase

/********************
 * @Author: Tiiee
 * @Date: 6/13/2023
 * @Time: 10:14 PM
 ********************/

data class MonitorUC(
	val getVehicleGroupByUser: GetVehicleGroupByUser,
	val getAllCarOnline: GetAllCarOnline,
	val getAllWarning: GetAllWarning,
	val getLstRunningSchedule: GetLstRunningSchedule,
	val getLstUpcomingSchedule: GetLstUpcomingSchedule,
	val getPointShortDataByGroup: GetPointShortDataByGroup,
	val getCarInfo: GetCarInfo,
	val getCarImage: GetCarImage,
)
