package com.hust.cartracking.core.components

import android.widget.Toast
import android.widget.Toast.makeText
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/********************
 * @Author: Tiiee
 * @Date: 5/26/2023
 * @Time: 8:47 PM
 ********************/

@Composable
fun ShowToast(
	message: String,
	short: Boolean = true
) = makeText(
	LocalContext.current,
	message,
	if (short) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
).show()