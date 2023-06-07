package com.hust.cartracking.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/********************
 * @Author: Tiiee
 * @Date: 6/5/2023
 * @Time: 5:10 PM
 ********************/

@Composable
fun Loading(
	isLoading: Boolean = false,
	fullScreen: Boolean = true
) {
	if (!isLoading) return
	
	if (fullScreen) {
		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			Box(
				modifier = Modifier
					.clip(RoundedCornerShape(8.dp))
					.background(Color.LightGray)
					.padding(18.dp),
				contentAlignment = Alignment.Center
			) {
				CircularProgressIndicator(
					color = Color.White,
				)
			}
		}
	} else {
		CircularProgressIndicator(
			color = Color.White,
		)
	}
}