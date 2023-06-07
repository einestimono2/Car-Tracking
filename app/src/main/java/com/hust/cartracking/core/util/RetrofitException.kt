package com.hust.cartracking.core.util

/********************
 * @Author: Tiiee
 * @Date: 5/31/2023
 * @Time: 9:46 PM
 ********************/

import com.hust.cartracking.R
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException


fun <T> handleException(e: Throwable): Resource<T> {
	Timber.v(e.message.toString())
	
	when (e) {
		// IO
		is IOException -> {
			return Resource.Error(
				uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
			)
		}
		
		// Http
		is HttpException -> {
			val errorResponse = convertErrorBody(e)
			
			return convertCanNullMessage(errorResponse)
		}
		
		else -> {
			return convertCanNullMessage(e.message)
		}
	}
}

private fun convertErrorBody(throwable: HttpException): String? {
	return try {
		throwable.response()?.errorBody()?.string()
	} catch (e: Exception) {
		e.message
	}
}

private fun <T> convertCanNullMessage(message: String?): Resource<T> {
	return message?.let { msg ->
		Resource.Error(
			uiText = UiText.StringValue(msg)
		)
	} ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
}