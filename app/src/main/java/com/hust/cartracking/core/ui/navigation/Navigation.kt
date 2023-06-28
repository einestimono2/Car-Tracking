package com.hust.cartracking.core.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.hust.cartracking.core.components.AppBarState
import com.hust.cartracking.core.util.Constants
import com.hust.cartracking.features.EmptyScreen
import com.hust.cartracking.features.auth.presentation.login.LoginScreen
import com.hust.cartracking.features.auth.presentation.splash.SplashScreen
import com.hust.cartracking.features.home.presentation.monitor.MonitorScreen
import com.hust.cartracking.features.home.presentation.monitor.viewmodel.MonitorViewModel
import com.hust.cartracking.features.home.presentation.monitor_search.MonitorSearchScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
	modifier: Modifier = Modifier,
	snackbarHostState: SnackbarHostState,
	navController: NavHostController,
	appBar: (AppBarState) -> Unit,
) {
	AnimatedNavHost(
		modifier = modifier,
		navController = navController,
		startDestination = Constants.START_DESTINATION,
	) {
		// Auth
		fadeTransitionComposable(route = Screens.SplashScreen.route) {
			SplashScreen(navController = navController)
		}
		slideTransitionComposable(route = Screens.LoginScreen.route) {
			LoginScreen(
				appBar = appBar,
				navController = navController,
				snackbarHostState = snackbarHostState
			)
		}
		
		// Home
		fadeTransitionComposable(route = Screens.HomeScreen.route) {
			val viewModel = hiltViewModel<MonitorViewModel>()
			
			MonitorScreen(
				navController = navController,
				snackbarHostState = snackbarHostState,
				appBar = appBar,
				viewModel = viewModel
			)
		}
		slideTransitionComposable(route = Screens.MonitorSearchScreen.route) {
			val parentEntry = remember(it) {
				navController.getBackStackEntry(Screens.HomeScreen.route)
			}
			val monitorViewModel = hiltViewModel<MonitorViewModel>(parentEntry)
			
			MonitorSearchScreen(
				navController = navController,
				snackbarHostState = snackbarHostState,
				appBar = appBar,
				cars = monitorViewModel.state.value.cars,
			)
		}
		
		//
		fadeTransitionComposable(route = Screens.HistoryByCarScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.HistoryByScheduleScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.AccountScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.PermissionScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.ScopeScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.ScopePermissionScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.ScopeDivisionScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.GroupManagementScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.UserUnitScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.PointScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.ScheduleSampleScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.ScheduleScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.RfidScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.DeviceScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.VehicleScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.VehicleMaintenanceScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.DriverScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.OwnerScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.AtmTechnicianScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.EscortScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.RecipientScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.SettingScreen.route) {
			EmptyScreen()
		}
		fadeTransitionComposable(route = Screens.PasswordScreen.route) {
			EmptyScreen()
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


// TRANSITIONS EFFECTS

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.slideTransitionComposable(
	route: String,
	arguments: List<NamedNavArgument> = emptyList(),
	deepLinks: List<NavDeepLink> = emptyList(),
	content: @Composable (entry: NavBackStackEntry?) -> Unit,
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
		content(it)
	}
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.fadeTransitionComposable(
	route: String,
	arguments: List<NamedNavArgument> = emptyList(),
	deepLinks: List<NavDeepLink> = emptyList(),
	content: @Composable (entry: NavBackStackEntry?) -> Unit,
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
		content(it)
	}
}