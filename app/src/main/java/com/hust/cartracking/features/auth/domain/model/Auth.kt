package com.hust.cartracking.features.auth.domain.model

/********************
 * @Author: Tiiee
 * @Date: 6/4/2023
 * @Time: 1:05 AM
 ********************/

data class Auth(
	val accessToken: String,
	val scope: String,
)
