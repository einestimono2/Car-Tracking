package com.hust.cartracking.features.home.presentation.car_info.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RotateLeft
import androidx.compose.material.icons.filled.RotateRight
import androidx.compose.material.icons.rounded.Redo
import androidx.compose.material.icons.rounded.Undo
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hust.cartracking.BuildConfig
import com.hust.cartracking.R
import com.hust.cartracking.core.ui.theme.darkGray
import com.hust.cartracking.core.util.LocalImage
import com.hust.cartracking.core.util.NetworkImage
import com.hust.cartracking.core.util.extensions.convertTime
import com.hust.cartracking.features.home.domain.model.CarImage
import kotlinx.coroutines.launch

/********************
 * @Author: Tiiee
 * @Date: 6/28/2023
 * @Time: 10:52 AM
 ********************/

@Composable
fun ImageWithRotate(
	modifier: Modifier,
	carImg: List<CarImage>,
) {
	val rotation = remember { Animatable(0f) }
	val scope = rememberCoroutineScope()
	
	var currentIdx by remember {
		mutableIntStateOf(1)
	}
	
	Column(
		modifier = modifier
			.fillMaxWidth()
			.border(1.dp, Color.LightGray)
	) {
		Box(
			modifier = Modifier
				.weight(1f)
				.fillMaxSize()
				.rotate(rotation.value)
		) {
			if (carImg.isEmpty()) {
				LocalImage(
					id = R.drawable.no_img_available,
					modifier = Modifier.fillMaxSize(),
				)
			} else {
				NetworkImage(
					url = getLinkImage(carImg[carImg.size - currentIdx].camImgPath),
					modifier = Modifier.fillMaxSize(),
				)
			}
			
			if (carImg.isNotEmpty() && (currentIdx != carImg.size)) {
				// Left
				IconButton(
					onClick = { currentIdx += 1 },
					modifier = Modifier
						.padding(start = 3.dp)
						.clip(CircleShape)
						.background(Color.Red)
						.align(Alignment.CenterStart)
						.size(26.dp)
				) {
					Icon(
						imageVector = Icons.Rounded.Undo,
						contentDescription = "",
						tint = Color.White,
						modifier = Modifier.padding(3.dp)
					)
				}
			}
			
			if (carImg.isNotEmpty() && (currentIdx != 1)) {
				// Right
				IconButton(
					onClick = { currentIdx -= 1 },
					modifier = Modifier
						.padding(start = 3.dp)
						.clip(CircleShape)
						.background(Color.Red)
						.align(Alignment.CenterEnd)
						.size(26.dp)
				) {
					Icon(
						imageVector = Icons.Rounded.Redo,
						contentDescription = "",
						tint = Color.White,
						modifier = Modifier.padding(3.dp)
					)
				}
			}
		}
		Box(
			contentAlignment = Alignment.Center,
			modifier = Modifier
				.fillMaxWidth()
				.height(30.dp)
				.background(darkGray)
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier.fillMaxSize(),
			) {
				Icon(
					Icons.Default.RotateRight,
					"",
					Modifier
						.fillMaxHeight()
						.width(30.dp)
						.padding(3.dp)
						.clickable {
							scope.launch {
								rotation.animateTo(
									targetValue = rotation.value + 90f,
									animationSpec = tween(500, easing = LinearEasing)
								)
							}
						},
					Color.White
				)
				
				Text(
					modifier = Modifier.weight(1f),
					color = Color.White,
					textAlign = TextAlign.Center,
					text = if (carImg.isEmpty()) "" else
						" ${carImg.size - currentIdx + 1}/${carImg.size} \t ${carImg[carImg.size - currentIdx].deviceTime.convertTime()}",
				)
				
				Icon(
					Icons.Default.RotateLeft,
					"",
					Modifier
						.fillMaxHeight()
						.width(30.dp)
						.padding(3.dp)
						.clickable {
							scope.launch {
								rotation.animateTo(
									targetValue = rotation.value - 90f,
									animationSpec = tween(500, easing = LinearEasing)
								)
							}
						},
					Color.White
				)
			}
		}
	}
}

fun getLinkImage(raw: String): String {
	return "${BuildConfig.BASE_URL}/${raw.replace("..\\", "").replace("\\", "/")}"
}
