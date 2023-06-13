package com.hust.cartracking.core.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import com.hust.cartracking.core.ui.navigation.Screens
import com.hust.cartracking.core.ui.navigation.pop
import kotlinx.coroutines.launch

/********************
 * @Author: Tiiee
 * @Date: 6/8/2023
 * @Time: 8:45 PM
 ********************/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
	navController: NavHostController,
	drawerState: DrawerState,
	currentRoute: String?
) {
	val scope = rememberCoroutineScope()
	
	if (showAppBar(currentRoute)) {
		val showArrowBack = showArrowBack(currentRoute)
		
		TopAppBar(
			title = {
				Text(
					text = Screens.getScreenName(currentRoute),
					style = MaterialTheme.typography.titleMedium,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis,
				)
			},
			navigationIcon = {
				IconButton(onClick = {
					if (showArrowBack) {
						navController.pop()
					} else {
						scope.launch { drawerState.open() }
					}
				}) {
					Icon(
						imageVector = if (showArrowBack) Icons.Default.ArrowBack else Icons.Default.Menu,
						contentDescription = "Menu"
					)
				}
			},
			colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
		)
	}
}

fun showAppBar(route: String?): Boolean {
	return when (route) {
		Screens.SplashScreen.route -> false
		Screens.LoginScreen.route -> false
		else -> true
	}
}

fun showArrowBack(route: String?): Boolean {
	return when (route) {
		Screens.SplashScreen.route -> false
		Screens.LoginScreen.route -> false
		else -> false
	}
}