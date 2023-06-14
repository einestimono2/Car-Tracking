package com.hust.cartracking.features.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import com.hust.cartracking.core.components.AppBarState

@Composable
fun HomeScreen(
	appBar: (AppBarState) -> Unit,
	navController: NavHostController,
) {
	LaunchedEffect(key1 = true) {
		appBar(
			AppBarState(
				showAppBar = true,
				title = "Giám sát",
				actions = {}
			)
		)
	}
	
	val mapProperties = MapProperties(
		// Only enable if user has accepted location permissions.
		isMyLocationEnabled = false//state.lastKnownLocation != null,
	)
	
	// Khởi tạo vị trí ban đầu
	val cameraPositionState = rememberCameraPositionState {
		position = CameraPosition.fromLatLngZoom(LatLng(21.028270208, 105.85189819), 13f)
	}
	
	Box(modifier = Modifier.fillMaxSize()) {
		GoogleMap(
			modifier = Modifier.fillMaxSize(),
			properties = mapProperties,
			cameraPositionState = cameraPositionState
		) {
		
		}
	}
}