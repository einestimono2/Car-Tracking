package com.hust.cartracking.features.index.data.repository

import com.hust.cartracking.core.data.local.AppCache
import com.hust.cartracking.core.util.Constants.MONITOR_INDEX_VALUE_KEY
import com.hust.cartracking.core.util.Constants.VERIFICATION_TOKEN_TAG
import com.hust.cartracking.core.util.Resource
import com.hust.cartracking.features.index.data.remote.IndexAPI
import com.hust.cartracking.features.index.domain.repository.IndexRepository
import timber.log.Timber
import javax.inject.Inject

/********************
 * @Author: Tiiee
 * @Date: 6/25/2023
 * @Time: 5:48 PM
 ********************/

class IndexRepositoryImpl @Inject constructor(
	private val api: IndexAPI,
	private val appCacheManager: AppCache
) : IndexRepository {
	
	override fun getMonitorIndex(): Resource<String> {
		val monitorValue = appCacheManager.readValue(MONITOR_INDEX_VALUE_KEY)
		
		if (monitorValue != null) {
			Timber.v("Monitor Index: $monitorValue")
			return Resource.Success(monitorValue)
		} else {
			api.getMonitorIndex().execute().let {
				val response = it.body()!!.string()
				
				val startIdx =
					response.indexOf(VERIFICATION_TOKEN_TAG) + VERIFICATION_TOKEN_TAG.length
				
				var value = ""
				
				for (i in startIdx until response.length) {
					if (response[i] == '\"') {
						break
					} else {
						value += response[i]
					}
				}
				
				Timber.v("Refresh Monitor Index: $value")
				appCacheManager.setValue(MONITOR_INDEX_VALUE_KEY, value)
				
				return Resource.Success(value)
				
			}
		}
		
	}
	
}