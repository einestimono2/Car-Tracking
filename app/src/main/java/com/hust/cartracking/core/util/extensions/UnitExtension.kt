package com.hust.cartracking.core.util.extensions

/********************
 * @Author: Tiiee
 * @Date: 5/25/2023
 * @Time: 9:57 PM
 ********************/

import android.content.res.Resources
import android.util.TypedValue
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Dp.toPx(): Float {
	return TypedValue.applyDimension(
		TypedValue.COMPLEX_UNIT_DIP,
		this.value,
		Resources.getSystem().displayMetrics
	)
}

fun Float.toDp(): Dp {
	return (this / Resources.getSystem().displayMetrics.density).dp
}
