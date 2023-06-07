package com.hust.cartracking.features.home.data.remote

import retrofit2.http.GET

/********************
 * @Author: Tiiee
 * @Date: 5/29/2023
 * @Time: 10:13 PM
 ********************/

interface HomeAPI {
	@GET("/api/user/authenticate")
	suspend fun authenticate()
}