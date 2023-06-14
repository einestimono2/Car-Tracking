package com.hust.cartracking.core.util

import com.hust.cartracking.core.ui.navigation.Screens

// Object class: Khởi tạo một lần để sử dụng cho cả chương trình <=> Singleton
object Constants {
	const val EMAIL_VALIDATION_REGEX = "^(.+)@(.+)\$"
	
	const val SHARED_PREF_NAME = "car_tracking_shared_pref"
	
	const val ACCESS_TOKEN_KEY = "access_token"

	val START_DESTINATION = Screens.HomeScreen.route
	
	// DEVELOP
	const val ASSESS_TOKEN_VALUE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIxIiwic2NvcGVJZCI6IjEiLCJ1c2VyTmFtZSI6ImRlbW9AZ21haWwuY29tIiwibmJmIjoxNjg2NjY2ODE2LCJleHAiOjE2ODY3MjA4MTYsImlhdCI6MTY4NjY2NjgxNn0._JpFIIBcAZ2CSwNpoVfzE6i5nO5LG2g2oAQpVVFf9ww"
	// INDEX: .AspNetCore.Antiforgery.dNc7gmZvK2I=
	const val MONITOR_INDEX_VALUE = "CfDJ8DK3sGQInfdBtBPy5xK5Zhocb1zMYvmZ4ft2pZ2v3z94ECmJCti2pnY9XG0poeHt73YavcAXSMcNCH0t9aFswZ873akrwmC4bdAZ34zAPzdZ5swh1KGgspnwjwDtEDQbAEH_98R9n5c-gMOK4Z970Xo"
}