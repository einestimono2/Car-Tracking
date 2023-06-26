package com.hust.cartracking.features.home.presentation.monitor

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.hust.cartracking.R
import com.hust.cartracking.core.components.AppBarState
import com.hust.cartracking.core.ui.navigation.Screens
import com.hust.cartracking.core.ui.navigation.push
import com.hust.cartracking.core.util.Constants.CAR_ONLINE_SEARCH_KEY
import com.hust.cartracking.core.util.UiEvent
import com.hust.cartracking.core.util.asString
import com.hust.cartracking.core.util.bitmapDescriptor
import com.hust.cartracking.features.home.domain.model.shortDescription
import com.hust.cartracking.features.home.presentation.monitor.components.CustomMarkerInfoWindow
import com.hust.cartracking.features.home.presentation.monitor.components.WarningCard
import com.hust.cartracking.features.home.presentation.monitor.components.WarningHeaderCard
import com.hust.cartracking.features.home.presentation.monitor.viewmodel.MonitorViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MonitorScreen(
	appBar: (AppBarState) -> Unit,
	navController: NavHostController,
	snackbarHostState: SnackbarHostState,
	viewModel: MonitorViewModel,
) {
	val state = viewModel.state.value
	val context = LocalContext.current
	
	LaunchedEffect(key1 = true) {
		appBar(AppBarState(title = "Giám sát", actions = {
			IconButton(onClick = { navController.push(Screens.MonitorSearchScreen.route) }) {
				Icon(Icons.Default.CalendarToday, contentDescription = "Lịch trình")
			}
			IconButton(onClick = { navController.push(Screens.MonitorSearchScreen.route) }) {
				Icon(Icons.Default.Search, contentDescription = "Tìm xe")
			}
		}))
		
		viewModel.eventFlow.collectLatest { event ->
			when (event) {
				is UiEvent.ShowSnackbar -> {
					snackbarHostState.showSnackbar(
						withDismissAction = true, message = event.uiText.asString(context)
					)
				}
				
				else -> {}
			}
		}
	}

//	DisposableEffect(Unit) {
//		viewModel.setIsActive(true)
//		onDispose {
//			viewModel.setIsActive(false)
//		}
//	}
	
	val mapProperties = MapProperties(
		// Only enable if user has accepted location permissions.
		isMyLocationEnabled = false//state.lastKnownLocation != null,
	)
	
	// Khởi tạo vị trí ban đầu
	val cameraPositionState = rememberCameraPositionState {
		position = CameraPosition.fromLatLngZoom(
			LatLng(21.028270208, 105.85189819), 13f
		)
	}
	
	val scope = rememberCoroutineScope()
	val scaffoldState = rememberBottomSheetScaffoldState()
	
	BottomSheetScaffold(
		scaffoldState = scaffoldState,
		sheetShape = RectangleShape,
		sheetPeekHeight = 80.dp,
		sheetContent = {
			Box(
				Modifier.fillMaxWidth(),
				contentAlignment = Alignment.Center
			) {
				Text(
					modifier = Modifier
						.fillMaxWidth()
						.padding(6.dp, 0.dp, 6.dp, 20.dp)
						.basicMarquee(spacing = MarqueeSpacing(22.dp)),
					text = if (state.warnings.isNotEmpty()) state.warnings[0].shortDescription else ""
				)
			}
			
			if (state.warnings.isNotEmpty()){
				WarningHeaderCard()
				
				LazyColumn(
					Modifier.fillMaxWidth(),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					items(state.warnings) {
						WarningCard(warning = it) {
							scope.launch {
								scaffoldState.bottomSheetState.partialExpand()
								
								cameraPositionState.position = CameraPosition.fromLatLngZoom(
									LatLng(it.endLatitude, it.endLongitude), 16f
								)
							}
						}
					}
				}
				
				Spacer(modifier = Modifier.height(18.dp))
			}
		}
	) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(it)
		) {
			GoogleMap(
				modifier = Modifier.fillMaxSize(),
				properties = mapProperties,
				cameraPositionState = cameraPositionState
			) {
				state.cars.forEach { car ->
					MarkerInfoWindow(
						state = MarkerState(
							if (car.gpsLat == 0.0 && car.gpsLon == 0.0) {
								LatLng(car.networkLat, car.networkLon)
							} else {
								LatLng(car.gpsLat, car.gpsLon)
							}
						),
						icon = bitmapDescriptor(
							context = context, vectorResId = when (car.stateName) {
								"SOS" -> R.drawable.sos
								"PARKING" -> R.drawable.do_
								else -> R.drawable.chay
							}
						)
					) {
						CustomMarkerInfoWindow(car, context)
					}
				}
			}
		}
	}
	
	val onCarClicked =
		navController.currentBackStackEntry
			?.savedStateHandle
			?.getLiveData<LatLng>(CAR_ONLINE_SEARCH_KEY)
			?.observeAsState()
	
	// Khi chọn xe từ màn hình search --> nhảy tới vị trị của xe đó
	onCarClicked?.value?.let {
		LaunchedEffect(it) {
			cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 16f)
			
			navController.currentBackStackEntry
				?.savedStateHandle
				?.remove<LatLng>(CAR_ONLINE_SEARCH_KEY)
		}
	}
}
