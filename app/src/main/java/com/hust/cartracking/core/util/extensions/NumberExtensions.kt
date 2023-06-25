package com.hust.cartracking.core.util.extensions

import java.math.RoundingMode
import java.text.DecimalFormat

/********************
 * @Author: Tiiee
 * @Date: 6/18/2023
 * @Time: 7:22 PM
 ********************/

val Double.roundTo2DecimalPlaces: String
	get() {
		val df = DecimalFormat("#.##")
		df.roundingMode = RoundingMode.CEILING
		
		return df.format(this)
	}