package com.hust.cartracking.features.home.presentation.monitor_search

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.LatLng
import com.hust.cartracking.core.components.AppBarState
import com.hust.cartracking.core.components.Loading
import com.hust.cartracking.core.ui.navigation.pop
import com.hust.cartracking.core.ui.theme.header_bg
import com.hust.cartracking.core.util.Constants.CAR_ONLINE_SEARCH_KEY
import com.hust.cartracking.core.util.UiEvent
import com.hust.cartracking.core.util.asString
import com.hust.cartracking.core.util.extensions.Border
import com.hust.cartracking.core.util.extensions.border
import com.hust.cartracking.core.util.extensions.clickableWithoutRipple
import com.hust.cartracking.features.home.domain.model.CarOnline
import com.hust.cartracking.features.home.domain.model.VehicleGroupByUser
import com.hust.cartracking.features.home.presentation.monitor.viewmodel.MonitorViewModel
import com.hust.cartracking.features.home.presentation.monitor_search.components.CarCard
import com.hust.cartracking.features.home.presentation.monitor_search.components.CarCardHeader
import com.hust.cartracking.features.home.presentation.monitor_search.components.ListVehicleGroup
import com.hust.cartracking.features.home.presentation.monitor_search.components.SearchBox
import com.hust.cartracking.features.home.presentation.monitor_search.viewmodel.MonitorSearchEvents
import com.hust.cartracking.features.home.presentation.monitor_search.viewmodel.MonitorSearchViewModel
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

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
							onClick = {
								val coordinates = if(item.gpsLat == 0.0 && item.gpsLon == 0.0) {
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
	}
	
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
	showBackground = true,
	backgroundColor = 256 * 256 * 256, // R * G * B
	showSystemUi = true,
	uiMode = Configuration.UI_MODE_TYPE_NORMAL, name = "MonitorSearch"
)
@Composable
fun AppDrawerPreview() {
	
	Surface() {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(12.dp, 8.dp, 12.dp)
		) {
			var expanded by remember { mutableStateOf(false) }
			val items = VehicleGroupByUser.vehicleGroupByUsers
			var currentValue by remember { mutableStateOf(items[0].name) }
			
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.fillMaxWidth()
					.height(IntrinsicSize.Min)
			) {
				Text(
					modifier = Modifier.weight(1f),
					text = "Đơn vị",
					textAlign = TextAlign.Center,
				)
				
				ExposedDropdownMenuBox(
					modifier = Modifier
						.wrapContentHeight()
						.weight(4f),
					expanded = expanded,
					onExpandedChange = { expanded = it },
				) {
					OutlinedTextField(
						modifier = Modifier
							.menuAnchor()
							.fillMaxWidth()
							.defaultMinSize(minHeight = 32.dp),
						value = currentValue,
						onValueChange = {},
						readOnly = true,
						trailingIcon = {
							ExposedDropdownMenuDefaults.TrailingIcon(
								expanded = expanded
							)
						},
					)
					
					ExposedDropdownMenu(
						expanded = expanded,
						onDismissRequest = { expanded = false },
						modifier = Modifier
							.requiredSizeIn(maxHeight = 300.dp)
					) {
						items.forEach { item ->
							DropdownMenuItem(
								text = { Text(item.name) },
								onClick = {
									currentValue = item.name
									expanded = false
								},
							)
						}
					}
				}
			}
			
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.padding(vertical = 6.dp)
					.fillMaxWidth()
					.height(IntrinsicSize.Min)
			) {
				Text(
					modifier = Modifier.weight(1f),
					text = "Tìm xe",
					textAlign = TextAlign.Center,
				)
				
				OutlinedTextField(
					singleLine = true,
					modifier = Modifier
						.weight(4f)
						.defaultMinSize(minHeight = 32.dp),
					value = "",
					onValueChange = {},
					textStyle = MaterialTheme.typography.bodyMedium,
					trailingIcon = {
						IconButton(
							onClick = {},
						) {
							Icon(
								imageVector = Icons.Filled.Close,
								contentDescription = "Xóa",
								modifier = Modifier.height(18.dp)
							)
						}
					}
				)
			}
			
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(top = 6.dp)
			) {
				// Header
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
							Text("#", textAlign = TextAlign.Center)
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
								"Biển số",
								modifier = Modifier
									.padding(vertical = 8.dp),
								textAlign = TextAlign.Center,
								style = MaterialTheme.typography.titleLarge.copy(
								),
							)
						}
						Box(
							contentAlignment = Alignment.Center,
							modifier = Modifier
								.weight(2f)
								.fillMaxHeight()
						) {
							Text(
								"Ảnh",
								
								textAlign = TextAlign.Center
							)
						}
					}
				}
				
				// Content
				LazyColumn(
					modifier = Modifier.fillMaxSize(),
					horizontalAlignment = Alignment.Start,
					verticalArrangement = Arrangement.spacedBy(2.dp),
				) {
					items(12) { item ->
						
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
									"1",
									textAlign = TextAlign.Center,
								)
							}
							Box(
								contentAlignment = Alignment.Center,
								modifier = Modifier
									.weight(6f)
									.background(Color.Red)
									.border(
										1.dp, Color.LightGray
									)
							) {
								Text(
									"Biển số",
									modifier = Modifier
										.padding(vertical = 8.dp),
									textAlign = TextAlign.Center,
									style = MaterialTheme.typography.titleLarge.copy(
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
									"Ảnh",
									textAlign = TextAlign.Center
								)
							}
						}
					}
				}
			}
		}
	}
	
}