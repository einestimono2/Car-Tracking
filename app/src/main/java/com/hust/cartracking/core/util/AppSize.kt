package com.hust.cartracking.core.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/********************
 * @Author: Tiiee
 * @Date: 6/5/2023
 * @Time: 3:04 PM
 ********************/

@Composable
fun initAppSize(): AppSize {
	val configuration = LocalConfiguration.current
	
	return AppSize(
		screenWidthInfo = when {
			configuration.screenWidthDp < 600 -> AppSize.WindowType.Compact
			configuration.screenWidthDp < 840 -> AppSize.WindowType.Medium
			else -> AppSize.WindowType.Expanded
		},
		screenHeightInfo = when {
			configuration.screenHeightDp < 480 -> AppSize.WindowType.Compact
			configuration.screenHeightDp < 900 -> AppSize.WindowType.Medium
			else -> AppSize.WindowType.Expanded
		},
		screenWidth = configuration.screenWidthDp.dp,
		screenHeight = configuration.screenHeightDp.dp
	)
}

data class AppSize(
	val screenWidthInfo: WindowType,
	val screenHeightInfo: WindowType,
	val screenWidth: Dp,
	val screenHeight: Dp
) {
	sealed class WindowType {
		object Compact : WindowType()
		object Medium : WindowType()
		object Expanded : WindowType()
	}
	
	fun percentWidth(percent: Float): Dp = screenWidth * percent
	
	fun percentHeight(percent: Float): Dp = screenHeight * percent
}

