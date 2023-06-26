package com.hust.cartracking.core.data.local

/********************
 * @Author: Tiiee
 * @Date: 5/31/2023
 * @Time: 10:25 PM
 ********************/

interface AppCache {
	
	fun setValue(
		key: String,
		value: String
	)
	
	fun readValue(
		key: String,
	): String?
	
	fun removeValue(
		key: String
	)

	fun clearAll()
	
	fun clearAllIndex()
}