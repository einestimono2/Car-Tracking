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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import coil.ImageLoader
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.hust.cartracking.core.components.AppBar
import com.hust.cartracking.core.components.AppBarState
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
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					val navController = rememberAnimatedNavController()
					
					val snackbarHostState = remember { SnackbarHostState() }
					
					val drawerState = rememberDrawerState(DrawerValue.Closed)
					var appBarState by remember { mutableStateOf(AppBarState()) }
					
					AppDrawer(
						navController = navController,
						drawerState = drawerState,
					) {
						Scaffold(
							modifier = Modifier.fillMaxSize(),
							snackbarHost = { SnackbarHost(snackbarHostState) },
							topBar = {
								AppBar(
									navController = navController,
									drawerState = drawerState,
									appBarState = appBarState,
								)
							}
						) {
							Navigation(
								modifier = Modifier.padding(it),
								snackbarHostState = snackbarHostState,
								imageLoader = imageLoader,
								navController = navController,
								appBar = { appBar -> appBarState = appBar },
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