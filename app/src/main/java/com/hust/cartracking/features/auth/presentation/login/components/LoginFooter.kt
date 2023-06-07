package com.hust.cartracking.features.auth.presentation.login.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hust.cartracking.core.util.extensions.heightRatio

/********************
 * @Author: Tiiee
 * @Date: 6/6/2023
 * @Time: 7:57 PM
 ********************/

@Composable
fun LoginFooter() {
	Text(text = "Copyright @Tiiee", style = MaterialTheme.typography.bodyMedium)
	Text(text = "2023", style = MaterialTheme.typography.bodyMedium)
	Spacer(modifier = Modifier.heightRatio(0.015f))
}