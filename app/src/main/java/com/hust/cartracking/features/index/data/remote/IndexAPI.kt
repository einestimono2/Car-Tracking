package com.hust.cartracking.features.index.data.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

/********************
 * @Author: Tiiee
 * @Date: 6/25/2023
 * @Time: 4:25 PM
 ********************/

interface IndexAPI {
	
	@GET("/Monitor/Index")
	fun getMonitorIndex(): Call<ResponseBody>
	
}