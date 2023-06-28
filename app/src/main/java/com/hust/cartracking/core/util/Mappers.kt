package com.hust.cartracking.core.util

import androidx.compose.ui.graphics.Color
import com.hust.cartracking.core.ui.theme.car_parking
import com.hust.cartracking.core.ui.theme.car_running
import com.hust.cartracking.core.ui.theme.car_sos
import com.hust.cartracking.core.ui.theme.car_stopping
import com.hust.cartracking.core.ui.theme.warning_level1
import com.hust.cartracking.core.ui.theme.warning_level2
import com.hust.cartracking.core.ui.theme.warning_level3
import com.hust.cartracking.core.ui.theme.warning_level4

/********************
 * @Author: Tiiee
 * @Date: 6/28/2023
 * @Time: 11:42 AM
 ********************/

fun mapCarStatus(type: String): String {
	return when (type) {
		"SOS" -> "BÁO ĐỘNG"
		"PARKING" -> "ĐỖ"
		else -> "CHẠY"
	}
}

fun mapCarType(type: Int?): String {
	return when (type) {
		1 -> "Chở tiền"
		2 -> "Dùng chung (không camera)"
		else -> "Không xác định"
	}
}

fun mapWarningColor(level: Int): Color {
	return when (level) {
		1 -> warning_level1
		2 -> warning_level2
		3 -> warning_level3
		4 -> warning_level4
		else -> Color.Gray
	}
}

fun mapBackgroundStateColor(stateName: String): Color {
	return when (stateName) {
		"RUNNING" -> car_running
		"SOS" -> car_sos
		"PARKING" -> car_parking
		else -> car_stopping
	}
}

fun mapTextStateColor(stateName: String): Color {
	return when (stateName) {
		"PARKING" -> Color(0xFFFFFF00)
		else -> Color.White
	}
}