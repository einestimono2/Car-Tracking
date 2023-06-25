package com.hust.cartracking.features.home.presentation.monitor_search.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/********************
 * @Author: Tiiee
 * @Date: 6/21/2023
 * @Time: 2:33 PM
 ********************/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(
	title: String,
	value: String,
	onValueChange: (String) -> Unit,
	onClear: () -> Unit,
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.padding(vertical = 6.dp)
			.fillMaxWidth()
			.height(IntrinsicSize.Min)
	) {
		Text(
			modifier = Modifier.weight(1f),
			text = title,
			textAlign = TextAlign.Center,
		)
		
		OutlinedTextField(
			singleLine = true,
			modifier = Modifier
				.weight(4f)
				.defaultMinSize(minHeight = 32.dp),
			value = value,
			onValueChange = onValueChange,
			textStyle = MaterialTheme.typography.bodyMedium,
			trailingIcon = {
				IconButton(onClick = onClear) {
					Icon(
						imageVector = Icons.Filled.Close,
						contentDescription = "Xóa",
						modifier = Modifier.height(18.dp)
					)
				}
			}
		)
	}
}