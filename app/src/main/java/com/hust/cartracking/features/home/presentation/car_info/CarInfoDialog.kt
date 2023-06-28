package com.hust.cartracking.features.home.presentation.car_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.hust.cartracking.core.components.Loading
import com.hust.cartracking.core.util.extensions.heightRatio
import com.hust.cartracking.core.util.mapCarType
import com.hust.cartracking.features.home.presentation.car_info.components.CarInfoCell
import com.hust.cartracking.features.home.presentation.car_info.components.ImageWithRotate
import com.hust.cartracking.features.home.presentation.car_info.viewmodel.CarInfoEvents
import com.hust.cartracking.features.home.presentation.car_info.viewmodel.CarInfoViewModel

/********************
 * @Author: Tiiee
 * @Date: 6/28/2023
 * @Time: 10:49 AM
 ********************/

@Composable
fun CarInfoDialog(
	carId: Int,
	viewModel: CarInfoViewModel = hiltViewModel(),
	onDismissRequest: () -> Unit,
) {
	val state = viewModel.state.value
	
	LaunchedEffect(key1 = true) {
		viewModel.onTriggerEvent(CarInfoEvents.GetCarInfo(carId))
		viewModel.onTriggerEvent(CarInfoEvents.GetCarImg(carId))
	}
	
	Dialog(
		onDismissRequest = onDismissRequest,
		properties = DialogProperties(
			dismissOnBackPress = true,
			dismissOnClickOutside = true
		)
	) {
		Card(
			elevation = CardDefaults.cardElevation(5.dp),
			shape = RoundedCornerShape(8.dp),
			modifier = Modifier
				.fillMaxWidth()
				.height(IntrinsicSize.Min)
		) {
			if (state.isLoading || state.carInfo == null) {
				Box(
					modifier = Modifier
						.fillMaxSize()
						.heightRatio(0.85f)
				) {
					Loading(true)
				}
			} else {
				Box(modifier = Modifier.fillMaxSize()) {
					Column(
						horizontalAlignment = Alignment.CenterHorizontally,
						modifier = Modifier
							.fillMaxSize()
							.padding(8.dp)
							.verticalScroll(rememberScrollState())
					) {
						Text(
							text = "Thông tin chi tiết xe",
							style = MaterialTheme.typography.titleLarge
						)
						
						Spacer(modifier = Modifier.height(6.dp))
						
						CarInfoCell(title = "Biển số", text = state.carInfo.licensePlate)
						CarInfoCell(title = "Loại xe", text = mapCarType(state.carInfo.carType))
						CarInfoCell(title = "Thiết bị", text = state.carInfo.imei)
						CarInfoCell(title = "Đơn vị", text = state.carInfo.unitName, isLast = true)
						
						Spacer(modifier = Modifier.height(28.dp))
						
						Text(
							text = "Hình ảnh xe",
							style = MaterialTheme.typography.titleLarge
						)
						
						Spacer(modifier = Modifier.height(6.dp))
						
						// Cam 1
						ImageWithRotate(
							modifier = Modifier.height(150.dp),
							carImg = state.carImgCam1,
						)
						
						Spacer(modifier = Modifier.height(8.dp))
						
						// Cam 2
						ImageWithRotate(
							modifier = Modifier.height(150.dp),
							carImg = state.carImgCam2,
						)
						
						Spacer(modifier = Modifier.height(16.dp))
						
					}
					
					IconButton(
						onClick = onDismissRequest,
						modifier = Modifier
							.padding(top = 6.dp, end = 6.dp)
							.clip(CircleShape)
							.background(Color.Red)
							.align(Alignment.TopEnd)
							.size(26.dp)
					) {
						Icon(
							imageVector = Icons.Rounded.Close,
							contentDescription = "",
							tint = Color.White,
							modifier = Modifier.padding(3.dp)
						)
					}
				}
			}
		}
	}
}