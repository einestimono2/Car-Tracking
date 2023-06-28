package com.hust.cartracking.features.home.presentation.monitor_search

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.LatLng
import com.hust.cartracking.core.components.AppBarState
import com.hust.cartracking.core.components.Loading
import com.hust.cartracking.core.ui.navigation.pop
import com.hust.cartracking.core.util.Constants.CAR_ONLINE_SEARCH_KEY
import com.hust.cartracking.core.util.UiEvent
import com.hust.cartracking.core.util.asString
import com.hust.cartracking.core.util.extensions.clickableWithoutRipple
import com.hust.cartracking.features.home.domain.model.CarOnline
import com.hust.cartracking.features.home.presentation.car_info.CarInfoDialog
import com.hust.cartracking.features.home.presentation.monitor_search.components.CarCard
import com.hust.cartracking.features.home.presentation.monitor_search.components.CarCardHeader
import com.hust.cartracking.features.home.presentation.monitor_search.components.ListVehicleGroup
import com.hust.cartracking.features.home.presentation.monitor_search.components.SearchBox
import com.hust.cartracking.features.home.presentation.monitor_search.viewmodel.MonitorSearchEvents
import com.hust.cartracking.features.home.presentation.monitor_search.viewmodel.MonitorSearchViewModel
import kotlinx.coroutines.flow.collectLatest

/********************
 * @Author: Tiiee
 * @Date: 6/18/2023
 * @Time: 7:51 PM
 ********************/

@Composable
fun MonitorSearchScreen(
	appBar: (AppBarState) -> Unit,
	navController: NavHostController,
	snackbarHostState: SnackbarHostState,
	viewModel: MonitorSearchViewModel = hiltViewModel(),
	cars: List<CarOnline>,
) {
	val context = LocalContext.current
	val currentFocus = LocalFocusManager.current
	val state = viewModel.state.value
	
	var currentValue by remember { mutableStateOf("Tất cả") }
	var keyword by remember { mutableStateOf("") }
	
	LaunchedEffect(key1 = true) {
		viewModel.onTriggerEvent(
			MonitorSearchEvents.SetListCarOnline(cars)
		)
		
		appBar(
			AppBarState(title = "Tìm kiếm phương tiện")
		)
		
		viewModel.eventFlow.collectLatest { event ->
			when (event) {
				is UiEvent.ShowSnackbar -> {
					snackbarHostState.showSnackbar(
						withDismissAction = true,
						message = event.uiText.asString(context)
					)
				}
				
				else -> {}
			}
		}
	}
	
	Box(
		contentAlignment = Alignment.Center,
		modifier = Modifier
			.fillMaxSize()
			.clickableWithoutRipple { currentFocus.clearFocus() }
	) {
		var showCarInfoDialog by remember {
			mutableIntStateOf(-1)
		}
		
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(12.dp, 8.dp, 12.dp)
		) {
			ListVehicleGroup(
				title = "Đơn vị",
				items = state.vehicleGroups,
				currentValue = currentValue,
				onClick = {
					currentValue = it
					viewModel.onTriggerEvent(
						MonitorSearchEvents.SearchCarOnline(
							keyword = keyword, unit = it
						)
					)
				}
			)
			
			SearchBox(
				title = "Tìm xe",
				value = keyword,
				onValueChange = {
					keyword = it
					viewModel.onTriggerEvent(
						MonitorSearchEvents.SearchCarOnline(
							keyword = keyword, unit = currentValue
						)
					)
				},
				onClear = {
					keyword = ""
					viewModel.onTriggerEvent(
						MonitorSearchEvents.SearchCarOnline(
							keyword = keyword, unit = currentValue
						)
					)
					currentFocus.clearFocus()
				}
			)
			
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(top = 12.dp)
			) {
				
				// Header
				CarCardHeader(
					id = "#",
					licensePlate = "Biển số",
				)
				
				// Content
				LazyColumn(
					modifier = Modifier.fillMaxSize(),
					horizontalAlignment = Alignment.Start,
					// verticalArrangement = Arrangement.spacedBy(6.dp),
				) {
					itemsIndexed(
						state.filterCars
					) { index, item ->
						
						CarCard(
							id = index.toString(),
							car = item,
							onImageClick = { showCarInfoDialog = item.carId },
							onClick = {
								val coordinates = if (item.gpsLat == 0.0 && item.gpsLon == 0.0) {
									LatLng(item.networkLat, item.networkLon)
								} else {
									LatLng(item.gpsLat, item.gpsLon)
								}
								
								navController.previousBackStackEntry?.savedStateHandle?.set(
									CAR_ONLINE_SEARCH_KEY, coordinates
								)
								navController.pop()
							}
						)
						
					}
				}
			}
		}
		
		Loading(isLoading = state.isLoading)
		
		if (showCarInfoDialog != -1) {
			CarInfoDialog(
				carId = showCarInfoDialog,
			) {
				showCarInfoDialog = -1
			}
		}
	}
	
}

@Preview(
	showBackground = true,
	backgroundColor = 256 * 256 * 256, // R * G * B
	showSystemUi = true,
	uiMode = Configuration.UI_MODE_TYPE_NORMAL, name = "MonitorSearch"
)
@Composable
fun AppDrawerPreview() {
	
	Surface() {
	
	}
	
}