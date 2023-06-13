package com.hust.cartracking.core.components

import androidx.compose.runtime.Composable

/********************
 * @Author: Tiiee
 * @Date: 6/7/2023
 * @Time: 9:20 AM
 ********************/
//
//@Composable
//fun MapCompose(
// state: MapState,
// setupClusterManager: (Context, GoogleMap) -> ZoneClusterManager,
// calculateZoneViewCenter: () -> LatLngBounds,
//) {
// // Set properties using MapProperties which you can use to recompose the map
// val mapProperties = MapProperties(
//  // Only enable if user has accepted location permissions.
//  isMyLocationEnabled = state.lastKnownLocation != null,
// )
// val cameraPositionState = rememberCameraPositionState()
// Box(
//  modifier = Modifier.fillMaxSize()
// ) {
//  GoogleMap(
//   modifier = Modifier.fillMaxSize(),
//   properties = mapProperties,
//   cameraPositionState = cameraPositionState
//  ) {
//   val context = LocalContext.current
//   val scope = rememberCoroutineScope()
//   MapEffect(state.clusterItems) { map ->
//    if (state.clusterItems.isNotEmpty()) {
//     val clusterManager = setupClusterManager(context, map)
//     map.setOnCameraIdleListener(clusterManager)
//     map.setOnMarkerClickListener(clusterManager)
//     state.clusterItems.forEach { clusterItem ->
//      map.addPolygon(clusterItem.polygonOptions)
//     }
//     map.setOnMapLoadedCallback {
//      if (state.clusterItems.isNotEmpty()) {
//       scope.launch {
//        cameraPositionState.animate(
//         update = CameraUpdateFactory.newLatLngBounds(
//          calculateZoneViewCenter(),
//          0
//         ),
//        )
//       }
//      }
//     }
//    }
//   }
//
//   // NOTE: Some features of the MarkerInfoWindow don't work currently. See docs:
//   // https://github.com/googlemaps/android-maps-compose#obtaining-access-to-the-raw-googlemap-experimental
//   // So you can use clusters as an alternative to markers.
//   MarkerInfoWindow(
//    state = rememberMarkerState(position = LatLng(49.1, -122.5)),
//    snippet = "Some stuff",
//    onClick = {
//     // This won't work :(
//     System.out.println("Mitchs_: Cannot be clicked")
//     true
//    },
//    draggable = true
//   )
//  }
// }
////    // Center camera to include all the Zones.
////    LaunchedEffect(state.clusterItems) {
////        if (state.clusterItems.isNotEmpty()) {
////            cameraPositionState.animate(
////                update = CameraUpdateFactory.newLatLngBounds(
////                    calculateZoneViewCenter(),
////                    0
////                ),
////            )
////        }
////    }
//}
//
///**
// * If you want to center on a specific location.
// */
//private suspend fun CameraPositionState.centerOnLocation(
// location: Location
//) = animate(
// update = CameraUpdateFactory.newLatLngZoom(
//  LatLng(location.latitude, location.longitude),
//  15f
// ),
//)