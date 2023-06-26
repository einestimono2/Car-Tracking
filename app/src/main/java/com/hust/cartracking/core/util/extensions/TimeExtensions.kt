package com.hust.cartracking.core.util.extensions

import android.annotation.SuppressLint
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

/********************
 * @Author: Tiiee
 * @Date: 6/18/2023
 * @Time: 5:22 PM
 ********************/

enum class TimeFormat {
	DEFAULT,
	YMD_HMS,
	YMD,
}

@SuppressLint("SimpleDateFormat")
fun String.convertTime(type: TimeFormat = TimeFormat.DEFAULT): String {
	val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
	
	return try {
		val d = sdf.parse(this)
		
		sdf.applyPattern(mapPattern(type))
		
		sdf.format(d!!)
	} catch (e: ParseException) {
		Timber.v("Exception", e.localizedMessage)
		
		this
	}
	
}

@SuppressLint("SimpleDateFormat")
fun Date.format(type: TimeFormat = TimeFormat.DEFAULT): String {
	val sdf = SimpleDateFormat("yyyyMMdd hhmmss")
	
	return try {
		sdf.applyPattern(mapPattern(type))
		
		sdf.format(this)
	} catch (e: ParseException) {
		Timber.v("Exception", e.localizedMessage)
		
		this.toString()
	}
}

val Calendar.today: Date
	get() {
		return this.time
	}

val Calendar.tomorrow: Date
	get() {
		this.add(Calendar.DAY_OF_YEAR, 1)
		return this.time
	}

val Calendar.tokenExpired: Date
	get() {
		this.add(Calendar.DAY_OF_YEAR, 1)
		this.add(Calendar.MINUTE, -30)
		return this.time
	}

fun mapPattern(type: TimeFormat): String {
	return when (type) {
		TimeFormat.YMD -> "yyyy-MM-dd"
		TimeFormat.YMD_HMS -> "dd/MM/yyyy, HH:mm:ss"
		else -> "HH:mm:ss, dd/MM/yyyy"
	}
}