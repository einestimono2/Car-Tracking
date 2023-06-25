package com.hust.cartracking.core.util.extensions

import android.annotation.SuppressLint
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

/********************
 * @Author: Tiiee
 * @Date: 6/18/2023
 * @Time: 5:22 PM
 ********************/

enum class TimeFormat {
	DEFAULT,
	YMD,
}

@SuppressLint("SimpleDateFormat")
fun String.convertTime(type: TimeFormat = TimeFormat.DEFAULT): String {
	val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
	
	return try {
		val d = sdf.parse(this)
		
		sdf.applyPattern(
			when (type) {
				TimeFormat.YMD -> "yyyy-MM-dd"
				else -> "HH:mm:ss, dd/MM/yyyy"
			}
		)
		
		sdf.format(d!!)
	} catch (e: ParseException) {
		Timber.v("Exception", e.localizedMessage)
		
		this
	}
	
}

@SuppressLint("SimpleDateFormat")
fun Date.today(type: TimeFormat = TimeFormat.YMD): String {
	val sdf = SimpleDateFormat("yyyyMMdd hhmmss")
	
	return try {
		sdf.applyPattern(
			when (type) {
				TimeFormat.YMD -> "yyyy-MM-dd"
				else -> "HH:mm:ss, dd/MM/yyyy"
			}
		)
		
		sdf.format(this)
	} catch (e: ParseException) {
		Timber.v("Exception", e.localizedMessage)
		
		this.toString()
	}
}