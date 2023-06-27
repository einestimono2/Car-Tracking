package com.hust.cartracking.features.home.presentation.monitor.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hust.cartracking.core.ui.theme.header_bg
import com.hust.cartracking.core.ui.theme.warning_level1
import com.hust.cartracking.core.ui.theme.warning_level2
import com.hust.cartracking.core.ui.theme.warning_level3
import com.hust.cartracking.core.ui.theme.warning_level4
import com.hust.cartracking.core.util.extensions.Border
import com.hust.cartracking.core.util.extensions.border
import com.hust.cartracking.core.util.extensions.convertTime
import com.hust.cartracking.features.home.domain.model.Warning

/********************
 * @Author: Tiiee
 * @Date: 6/24/2023
 * @Time: 9:33 PM
 ********************/

@Composable
fun WarningCard(
	warning: Warning,
	onClick: () -> Unit,
) {
	Row(
		modifier = Modifier
			.height(IntrinsicSize.Min)
			.padding(horizontal = 16.dp)
			.clickable(onClick = onClick)
	) {
		Box(
			contentAlignment = Alignment.Center,
			modifier = Modifier
				.weight(1f)
				.fillMaxHeight()
				.border(
					1.dp, Color.LightGray
				)
		) {
			Text(
				warning.licensePlate,
				textAlign = TextAlign.Center,
			)
		}
		
		Box(
			contentAlignment = Alignment.Center,
			modifier = Modifier
				.weight(1f)
				.border(
					1.dp, Color.LightGray
				)
				.fillMaxHeight()
		) {
			Text(
				warning.scheduleName,
				textAlign = TextAlign.Center,
			)
		}
		
		Box(
			contentAlignment = Alignment.Center,
			modifier = Modifier
				.weight(1f)
				.fillMaxHeight()
				.border(
					1.dp, Color.LightGray
				)
		) {
			Text(
				warning.startTime.convertTime(),
				textAlign = TextAlign.Center
			)
		}
		
		Box(
			contentAlignment = Alignment.Center,
			modifier = Modifier
				.weight(1f)
				.fillMaxHeight()
				.border(
					1.dp, Color.LightGray
				)
		) {
			Text(
				warning.endTime.convertTime(),
				textAlign = TextAlign.Center
			)
		}
		
		Box(
			contentAlignment = Alignment.Center,
			modifier = Modifier
				.weight(2f)
				.fillMaxHeight()
				.background(mapWarningColor(warning.warningLevel))
				.border(
					1.dp, Color.LightGray
				)
		) {
			Text(
				warning.warningName,
				textAlign = TextAlign.Center,
				style = MaterialTheme.typography.bodyLarge.copy(
					color = Color.White
				)
			)
		}
	}
}

@Composable
fun WarningHeaderCard() {
	Card(
		colors = CardDefaults.cardColors(
			containerColor = header_bg
		),
		shape = RoundedCornerShape(
			topEnd = 8.dp,
			topStart = 8.dp,
			bottomEnd = 0.dp,
			bottomStart = 0.dp
		),
		modifier = Modifier
			.fillMaxWidth()
			.height(IntrinsicSize.Min)
			.padding(horizontal = 16.dp)
			.border(
				1.dp,
				Color.LightGray,
				shape = RoundedCornerShape(
					topEnd = 8.dp,
					topStart = 8.dp,
					bottomEnd = 0.dp,
					bottomStart = 0.dp
				)
			),
	) {
		Row(
			modifier = Modifier.height(IntrinsicSize.Min)
		) {
			Box(
				contentAlignment = Alignment.Center,
				modifier = Modifier
					.weight(1f)
					.fillMaxHeight()
			) {
				Text(
					"Biển số",
					textAlign = TextAlign.Center,
					modifier = Modifier.padding(vertical = 12.dp)
				)
			}
			Box(
				contentAlignment = Alignment.Center,
				modifier = Modifier
					.weight(1f)
					.fillMaxHeight()
					.border(
						start = Border(1.dp, Color.LightGray),
						end = Border(1.dp, Color.LightGray)
					)
			) {
				Text(
					"Tuyến", textAlign = TextAlign.Center,
				)
			}
			Box(
				contentAlignment = Alignment.Center,
				modifier = Modifier
					.weight(1f)
					.fillMaxHeight()
			) {
				Text(
					"Bắt đầu",
					textAlign = TextAlign.Center
				)
			}
			Box(
				contentAlignment = Alignment.Center,
				modifier = Modifier
					.weight(1f)
					.fillMaxHeight()
					.border(
						start = Border(1.dp, Color.LightGray),
						end = Border(1.dp, Color.LightGray)
					)
			) {
				Text(
					"Kết thúc",
					textAlign = TextAlign.Center
				)
			}
			Box(
				contentAlignment = Alignment.Center,
				modifier = Modifier
					.weight(2f)
					.fillMaxHeight()
			) {
				Text(
					"Cảnh báo",
					textAlign = TextAlign.Center
				)
			}
		}
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