package com.hust.cartracking.core.ui.navigation

sealed class Screens(val route: String) {
	object SplashScreen : Screens("splash_screen")
	object LoginScreen : Screens("login_screen")
	object HomeScreen : Screens("home_screen")
	
	fun withArgs(vararg args: String): String {
		return buildString {
			append(route)
			args.forEach { arg -> append("/$arg") }
		}
	}
}
