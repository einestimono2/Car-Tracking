package com.hust.cartracking.features.index.domain.repository

import com.hust.cartracking.core.util.Resource

/********************
 * @Author: Tiiee
 * @Date: 6/25/2023
 * @Time: 5:45 PM
 ********************/

interface IndexRepository {
	
	fun getMonitorIndex(): Resource<String>
	
}