package com.hust.cartracking.core.util.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp


/********************
 * @Author: Tiiee
 * @Date: 6/5/2023
 * @Time: 2:33 PM
 ********************/

fun Modifier.clickableWithoutRipple(onClick: () -> Unit): Modifier = composed {
	clickable(
		indication = null,
		interactionSource = remember { MutableInteractionSource() },
	) {
		onClick()
	}
}

fun Modifier.heightRatio(ratio: Float): Modifier = composed {
	height(
		LocalConfiguration.current.screenHeightDp.dp * ratio
	)
}

fun Modifier.widthRatio(ratio: Float): Modifier = composed {
	width(
		LocalConfiguration.current.screenWidthDp.dp * ratio
	)
}

fun Modifier.paddingRatio(ratio: Float): Modifier = composed {
	padding(
		LocalConfiguration.current.screenWidthDp.dp * ratio
	)
}