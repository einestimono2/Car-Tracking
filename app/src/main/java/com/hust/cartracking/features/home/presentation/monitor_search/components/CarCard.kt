package com.hust.cartracking.features.home.presentation.monitor_search.components

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
import com.hust.cartracking.core.ui.theme.car_parking
import com.hust.cartracking.core.ui.theme.car_running
import com.hust.cartracking.core.ui.theme.car_sos
import com.hust.cartracking.core.ui.theme.car_stopping
import com.hust.cartracking.core.ui.theme.header_bg
import com.hust.cartracking.core.util.extensions.Border
import com.hust.cartracking.core.util.extensions.border
import com.hust.cartracking.core.util.extensions.roundTo2DecimalPlaces
import com.hust.cartracking.features.home.domain.model.CarOnline

/********************
 * @Author: Tiiee
 * @Date: 6/21/2023
 * @Time: 2:37 PM
 ********************/

@Composable
fun CarCardHeader(
	id: String,
	licensePlate: String,
) {
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
				Text(id, textAlign = TextAlign.Center)
			}
			Box(
				contentAlignment = Alignment.Center,
				modifier = Modifier
					.weight(6f)
					.fillMaxHeight()
					.border(
						start = Border(1.dp, Color.LightGray),
						end = Border(1.dp, Color.LightGray),
					)
			) {
				Text(
					licensePlate,
					modifier = Modifier
						.padding(vertical = 12.dp),
					textAlign = TextAlign.Center,
					style = MaterialTheme.typography.titleMedium,
				)
			}
			Box(
				contentAlignment = Alignment.Center,
				modifier = Modifier
					.weight(2f)
					.fillMaxHeight()
			) {
				Text(
					"-",
					
					textAlign = TextAlign.Center
				)
			}
		}
	}
}

@Composable
fun CarCard(
	id: String,
	car: CarOnline,
	onClick: () -> Unit,
) {
	
	Row(modifier = Modifier.height(IntrinsicSize.Min)) {
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
				id,
				textAlign = TextAlign.Center,
			)
		}
		
		Box(
			contentAlignment = Alignment.Center,
			modifier = Modifier
				.weight(6f)
				.background(mapBackgroundColor(car.stateName))
				.border(
					1.dp, Color.LightGray
				)
				.fillMaxHeight()
				.clickable(onClick = onClick)
		) {
			Text(
				car.licensePlate,
				modifier = Modifier
					.padding(vertical = 8.dp),
				textAlign = TextAlign.Center,
				style = MaterialTheme.typography.titleMedium.copy(
					color = mapTextColor(car.stateName)
				)
			)
		}
		
		Box(
			contentAlignment = Alignment.Center,
			modifier = Modifier
				.weight(2f)
				.fillMaxHeight()
				.border(
					1.dp, Color.LightGray
				)
		) {
			Text(
				"${car.stateName}",
				textAlign = TextAlign.Center
			)
		}
	}
}

fun mapBackgroundColor(stateName: String): Color {
	return when (stateName) {
		"RUNNING" -> car_running
		"SOS" -> car_sos
		"PARKING" -> car_parking
		else -> car_stopping
	}
}

fun mapTextColor(stateName: String): Color {
	return when (stateName) {
		"PARKING" -> Color(0xFFFFFF00)
		else -> Color.White
	}
}