package com.hust.cartracking.core.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import coil.ImageLoader
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.hust.cartracking.features.auth.presentation.login.LoginScreen
import com.hust.cartracking.features.auth.presentation.splash.SplashScreen
import com.hust.cartracking.features.home.presentation.HomeScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
	snackbarHostState: SnackbarHostState,
	imageLoader: ImageLoader,
) {
	val navController = rememberAnimatedNavController()
	
	AnimatedNavHost(
		navController = navController,
		startDestination = Screens.SplashScreen.route,
	) {
		fadeTransitionComposable(route = Screens.SplashScreen.route) {
			SplashScreen(navController = navController)
		}
		slideTransitionComposable(route = Screens.LoginScreen.route) {
			LoginScreen(navController = navController, snackbarHostState = snackbarHostState)
		}
		fadeTransitionComposable(route = Screens.HomeScreen.route) {
			HomeScreen(navController = navController)
		}
	}
}

// EXTENSIONS

fun NavHostController.go(route: String) = navigate(route) {
	popUpTo(graph.id) {
		inclusive = true
	}
}

fun NavHostController.push(route: String) = navigate(route)

fun NavHostController.pop() = popBackStack()


//fun EnterAnimation(content: @Composable () -> Unit) {
//	AnimatedVisibility(
//		visible = true,
//		enter = slideInVertically(
//			initialOffsetY = { -40 }
//		) + expandVertically(
//			expandFrom = Alignment.Top
//		) + fadeIn(initialAlpha = 0.3f),
//		exit = slideOutVertically() + shrinkVertically() + fadeOut(),
//		content = content,
//		initiallyVisible = false
//	)
//}
//
//NavHost(
//navController = navController,
//startDestination = "dest1"
//) {
//	composable("dest1") {
//		EnterAnimation {
//			FirstScreen(navController)
//		}
//	}
//	composable("dest2") {
//		EnterAnimation {
//			SecondScreen(navController)
//		}
//	}
//}

// TRANSITIONS EFFECTS

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.slideTransitionComposable(
	route: String,
	arguments: List<NamedNavArgument> = emptyList(),
	deepLinks: List<NavDeepLink> = emptyList(),
	content: @Composable () -> Unit,
) {
	composable(
		route = route,
		arguments = arguments,
		deepLinks = deepLinks,
		enterTransition = {
			slideIntoContainer(
				AnimatedContentTransitionScope.SlideDirection.Left,
				animationSpec = tween(500)
			) + fadeIn(
				animationSpec = tween(500)
			)
		},
		exitTransition = {
			slideOutOfContainer(
				AnimatedContentTransitionScope.SlideDirection.Left,
				animationSpec = tween(400)
			) + fadeOut(
				animationSpec = tween(400)
			)
		},
		popEnterTransition = {
			slideIntoContainer(
				AnimatedContentTransitionScope.SlideDirection.Right,
				animationSpec = tween(500)
			) + fadeIn(
				animationSpec = tween(500)
			)
		},
		popExitTransition = {
			slideOutOfContainer(
				AnimatedContentTransitionScope.SlideDirection.Right,
				animationSpec = tween(400)
			) + fadeOut(
				animationSpec = tween(400)
			)
		},
	) {
		content()
	}
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.fadeTransitionComposable(
	route: String,
	arguments: List<NamedNavArgument> = emptyList(),
	deepLinks: List<NavDeepLink> = emptyList(),
	content: @Composable () -> Unit,
) {
	composable(
		route = route,
		arguments = arguments,
		deepLinks = deepLinks,
		enterTransition = {
			fadeIn(animationSpec = tween(500))
		},
		exitTransition = {
			fadeOut(animationSpec = tween(400))
		},
		popEnterTransition = {
			fadeIn(animationSpec = tween(500))
		},
		popExitTransition = {
			fadeOut(animationSpec = tween(400))
		},
	) {
		content()
	}
}