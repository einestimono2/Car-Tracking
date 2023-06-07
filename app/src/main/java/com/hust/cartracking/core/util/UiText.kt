package com.hust.cartracking.core.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.hust.cartracking.R

/********************
 * @Author: Tiiee
 * @Date: 5/25/2023
 * @Time: 9:41 PM
 ********************/

sealed class UiText {
	data class StringValue(val str: String) : UiText()
	
	class StringResource(
		@StringRes val resourceId: Int,
		vararg val args: Any
	) : UiText()
	
	companion object {
		fun unknownError(): UiText {
			return UiText.StringResource(R.string.error_unknown)
		}
	}
}

@Composable
fun UiText.asString(): String {
	return when(this) {
		is UiText.StringValue -> this.str
		is UiText.StringResource -> stringResource(id = this.resourceId)
	}
}

fun UiText.asString(context: Context): String {
	return when(this) {
		is UiText.StringValue -> this.str
		is UiText.StringResource -> context.getString(this.resourceId)
	}
}