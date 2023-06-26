package com.hust.cartracking.core.data.local

import android.content.Context
import android.content.SharedPreferences
import com.hust.cartracking.core.util.Constants
import com.hust.cartracking.core.util.Constants.ACCESS_TOKEN_EXPIRED_KEY
import com.hust.cartracking.core.util.Constants.MONITOR_INDEX_VALUE_KEY
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
	
	override fun removeValue(key: String) = prefs.edit().remove(key).apply()
	
	override fun clearAll() = prefs.edit().clear().apply()
	override fun clearAllIndex() {
		removeValue(ACCESS_TOKEN_EXPIRED_KEY)
		removeValue(MONITOR_INDEX_VALUE_KEY)
	}
}