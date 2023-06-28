package com.hust.cartracking.features.home.presentation.car_info.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hust.cartracking.core.util.extensions.Border
import com.hust.cartracking.core.util.extensions.border

/********************
 * @Author: Tiiee
 * @Date: 6/28/2023
 * @Time: 10:51 AM
 ********************/

@Composable
fun CarInfoCell(
	title: String,
	text: String,
	isLast: Boolean = false
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.border(
				top = Border(1.dp, Color.LightGray),
				start = Border(1.dp, Color.LightGray),
				end = Border(1.dp, Color.LightGray),
				bottom = if (isLast) Border(1.dp, Color.LightGray) else null,
			)
	) {
		Box(
			modifier = Modifier
				.weight(1f)
				.border(end = Border(1.dp, Color.LightGray))
		) {
			Text(
				text = title,
				modifier = Modifier.padding(8.dp)
			)
		}
		Box(
			contentAlignment = Alignment.CenterStart,
			modifier = Modifier.weight(3f)
		) {
			Text(
				text,
				modifier = Modifier
					.padding(8.dp)
					.fillMaxWidth()
			)
		}
	}
}
