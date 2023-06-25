package com.hust.cartracking.core.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

/********************
 * @Author: Tiiee
 * @Date: 6/14/2023
 * @Time: 8:40 PM
 ********************/

data class AppBarState(
	val title: String = "",
	val actions: (@Composable RowScope.() -> Unit)? = null
)
