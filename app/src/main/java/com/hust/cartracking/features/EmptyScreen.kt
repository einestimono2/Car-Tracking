package com.hust.cartracking.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/********************
 * @Author: Tiiee
 * @Date: 6/18/2023
 * @Time: 7:36 PM
 ********************/

@Composable
fun EmptyScreen() {
	Box(
		modifier = Modifier.fillMaxSize(),
		contentAlignment = Alignment.Center,
	) {
		Text("Empty Screen")
	}
}