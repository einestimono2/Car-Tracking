package com.hust.cartracking.core.data.local

import android.content.Context
import android.content.SharedPreferences
import com.hust.cartracking.core.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/********************
 * @Author: Tiiee
 * @Date: 5/31/2023
 * @Time: 10:26 PM
 ********************/

class AppCacheManager @Inject constructor(
	@ApplicationContext val context: Context,
) : AppCache {
	
	private val prefs: SharedPreferences = context.getSharedPreferences(
		Constants.SHARED_PREF_NAME,
		Context.MODE_PRIVATE
	)
	
	override fun setValue(
		key: String,
		value: String
	) =
		prefs.edit().putString(key, value).apply()
	
	
	override fun readValue(
		key: String,
	): String? =
		prefs.getString(key, null)
}