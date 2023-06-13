package com.hust.cartracking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.ImageLoader
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.hust.cartracking.core.components.AppBar
import com.hust.cartracking.core.components.AppDrawer
import com.hust.cartracking.core.ui.navigation.Navigation
import com.hust.cartracking.core.ui.theme.CarTrackingTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	@Inject
	lateinit var imageLoader: ImageLoader
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		setContent {
			CarTrackingTheme() {
				val snackbarHostState = remember { SnackbarHostState() }
				val navController = rememberAnimatedNavController()
				val drawerState = rememberDrawerState(DrawerValue.Closed)
				val currentRoute =
					navController.currentBackStackEntryAsState().value?.destination?.route
				
				AppDrawer(
					navController = navController,
					drawerState = drawerState,
					currentRoute = currentRoute
				) {
					Scaffold(
						modifier = Modifier.fillMaxSize(),
						snackbarHost = { SnackbarHost(snackbarHostState) },
						topBar = {
							AppBar(
								navController = navController,
								currentRoute = currentRoute,
								drawerState = drawerState
							)
						}
					) {
						// A surface container using the 'background' color from the theme
						Surface(
							modifier = Modifier.padding(it),
							color = MaterialTheme.colorScheme.background
						) {
							Navigation(
								snackbarHostState = snackbarHostState,
								imageLoader = imageLoader,
								navController = navController,
							)
						}
					}
				}
				
			}
		}
	}
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//	CarTrackingTheme {
//		Surface(
//			modifier = Modifier.fillMaxSize(),
//			color = MaterialTheme.colorScheme.background
//		) {
//			val navController = rememberNavController()
//			Navigation(navController = navController)
//		}
//	}
//}