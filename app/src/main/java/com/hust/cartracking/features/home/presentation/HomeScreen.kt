package com.hust.cartracking.features.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun HomeScreen(navController: NavHostController) {
	val mapProperties = MapProperties(
		// Only enable if user has accepted location permissions.
		isMyLocationEnabled = false//state.lastKnownLocation != null,
	)
	val cameraPositionState = rememberCameraPositionState()
	
	Box(modifier = Modifier.fillMaxSize()) {
		GoogleMap(
			modifier = Modifier.fillMaxSize(),
			properties = mapProperties,
			cameraPositionState = cameraPositionState
		) {
		
		}
	}
}