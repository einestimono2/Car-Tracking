package com.hust.cartracking.features.home.presentation.monitor_search.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hust.cartracking.features.home.domain.model.VehicleGroupByUser

/********************
 * @Author: Tiiee
 * @Date: 6/18/2023
 * @Time: 10:35 PM
 ********************/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListVehicleGroup(
	title: String,
	items: List<VehicleGroupByUser>,
	currentValue: String,
	onClick: (String) -> Unit,
) {
	val all = listOf(VehicleGroupByUser(-1, "Tất cả"))
	val _items = if(items.isEmpty()) all else all + items
	
	var expanded by remember { mutableStateOf(false) }
	
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.fillMaxWidth()
			.height(IntrinsicSize.Min)
	) {
		Text(
			modifier = Modifier.weight(1f),
			text = title,
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
				modifier = Modifier.menuAnchor().fillMaxWidth().defaultMinSize(minHeight = 32.dp),
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
				_items.forEach { item ->
					DropdownMenuItem(
						text = { Text(item.name) },
						onClick = {
							expanded = false
							
							onClick.invoke(item.name)
						},
					)
				}
			}
		}
	}
}