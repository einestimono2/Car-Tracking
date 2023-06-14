package com.hust.cartracking.core.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
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
	appBarState: AppBarState,
) {
	val scope = rememberCoroutineScope()
	
	if (appBarState.showAppBar) {
		
		TopAppBar(
			title = {
				Text(
					text = appBarState.title,
					style = MaterialTheme.typography.titleMedium,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis,
				)
			},
			
			navigationIcon = {
				IconButton(onClick = {
					if (appBarState.isMenuNavigation) {
						scope.launch { drawerState.open() }
					} else {
						navController.pop()
					}
				}) {
					Icon(
						imageVector = if (!appBarState.isMenuNavigation) Icons.Default.ArrowBack else Icons.Default.Menu,
						contentDescription = "Menu"
					)
				}
			},
			
			actions = {
				appBarState.actions?.invoke(this)
			},
			
			colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
		)
	}
}