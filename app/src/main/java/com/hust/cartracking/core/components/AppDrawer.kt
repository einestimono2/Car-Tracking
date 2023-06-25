package com.hust.cartracking.core.components

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.hust.cartracking.R
import com.hust.cartracking.core.ui.navigation.go
import com.hust.cartracking.core.util.MenuDrawerItem
import com.hust.cartracking.core.util.extensions.widthRatio
import kotlinx.coroutines.launch
import timber.log.Timber

/********************
 * @Author: Tiiee
 * @Date: 6/7/2023
 * @Time: 5:48 PM
 ********************/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(
	navController: NavHostController,
	drawerState: DrawerState,
	content: @Composable () -> Unit,
) {
	val currentRoute =
		navController.currentBackStackEntryAsState().value?.destination?.route
	
	ModalNavigationDrawer(
		gesturesEnabled = drawerState.isOpen,
		content = content,
		drawerState = drawerState,
		drawerContent = {
			ModalDrawerSheet(
				modifier = Modifier
					.widthRatio(0.65f)
					.padding(top = 64.dp), // Drawer nằm bên dưới Appbar (64.dp: height của TopAppBar)
				drawerContainerColor = MaterialTheme.colorScheme.background,
				drawerShape = RoundedCornerShape(bottomEnd = 16.dp, topEnd = 16.dp),
			) {
				Column {
					DrawerHeader()
					Box(
						modifier = Modifier
							.padding(top = 12.dp)
							.fillMaxWidth()
							.height(0.75.dp)
							.background(Color.Gray),
					)
					DrawerContent(MenuDrawerItem.items, currentRoute, navController, drawerState)
				}
			}
		})
	
}

@Composable
fun DrawerHeader() {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight()
			.padding(8.dp, 18.dp, 8.dp, 0.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		Image(
			modifier = Modifier
				.clip(CircleShape)
				.widthRatio(0.25f)
				.border(2.dp, Color.LightGray, CircleShape),
			contentScale = ContentScale.FillWidth,
			painter = painterResource(id = R.drawable.admin),
			contentDescription = "Admin Image",
		)
		Spacer(modifier = Modifier.height(8.dp))
		Text("Quản trị viên", style = MaterialTheme.typography.titleMedium)
	}
	
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(
	items: List<MenuDrawerItem>,
	currentRoute: String?,
	navController: NavHostController,
	drawerState: DrawerState,
) {
	LazyColumn(
		modifier = Modifier
			.fillMaxWidth()
			.fillMaxHeight(),
		horizontalAlignment = Alignment.CenterHorizontally,
		contentPadding = PaddingValues(horizontal = 8.dp),
	) {
		items(items) { item ->
			DrawerItemWrapper(item, currentRoute, navController, drawerState)
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerItemWrapper(
	item: MenuDrawerItem,
	currentRoute: String?,
	navController: NavHostController,
	drawerState: DrawerState,
) {
	val hasChildren = item.children.isNotEmpty()
	val initExpanded = item.children.any { it.route == currentRoute }
	
	var expanded by remember { mutableStateOf(initExpanded) }
	val scope = rememberCoroutineScope()
	
	val onClick = {
		if (hasChildren) expanded = !expanded
		else if (item.route != currentRoute) {
			scope.launch { drawerState.close() }
			navController.go(item.route)
		}
	}
	
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight()
			.padding(vertical = 12.dp),
		shape = RoundedCornerShape(4.dp),
		colors = CardDefaults.cardColors(
			containerColor = Color.Black.copy(alpha = 0.15f)
		)
	) {
		Column(
			modifier = Modifier
				.padding(6.dp)
				.animateContentSize(
					animationSpec = spring(
						dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessLow
					)
				),
		) {
			DrawerItemParent(onClick, expanded, item, initExpanded || item.route == currentRoute)
			
			if (expanded && hasChildren) {
				Box(
					modifier = Modifier
						.fillMaxWidth()
						.padding(top = 1.dp, bottom = 6.dp)
						.height(0.5.dp)
						.background(Color.Gray),
				)
				
				item.children.forEach {
					DrawerItemChild(it, it.route == currentRoute) {
						if (it.route != currentRoute) {
							scope.launch { drawerState.close() }
							navController.go(it.route)
						}
					}
				}
			}
		}
	}
}

@Composable
fun DrawerItemParent(
	onClick: () -> Unit, expanded: Boolean, item: MenuDrawerItem, isSelected: Boolean
) {
	Row(
		modifier = Modifier
			.clickable { onClick() }
			.fillMaxWidth()
			.padding(vertical = 6.dp),
		verticalAlignment = Alignment.CenterVertically,
	) {
		Icon(
			imageVector = item.icon,
			contentDescription = "Menu Icon",
			tint = if (isSelected) Color.Red else Color.Black
		)
		Text(
			modifier = Modifier
				.padding(horizontal = 6.dp)
				.weight(1f),
			text = item.title,
			color = if (isSelected) Color.Red else Color.Black,
			style = MaterialTheme.typography.titleSmall
		)
		if (item.children.isNotEmpty()) Icon(
			imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
			contentDescription = "Person Icon",
			tint = if (isSelected) Color.Red else Color.Black
		)
	}
}

@Composable
fun DrawerItemChild(item: MenuDrawerItem, isSelected: Boolean, onClick: () -> Unit) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(22.dp, 6.dp, 6.dp, 3.dp)
			.clickable { onClick() },
		verticalAlignment = Alignment.CenterVertically,
	) {
		Icon(
			imageVector = item.icon,
			contentDescription = "Menu Icon",
			tint = if (isSelected) Color.Red else Color.Black
		)
		Text(
			modifier = Modifier
				.padding(start = 6.dp)
				.weight(1f),
			text = item.title,
			color = if (isSelected) Color.Red else Color.Black,
			style = MaterialTheme.typography.titleSmall
		)
	}
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Preview(
	showBackground = true, backgroundColor = 256 * 256 * 256, // R * G * B
	showSystemUi = true, name = "Drawer Screen", uiMode = Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun AppDrawerPreview() {
	AppDrawer(rememberAnimatedNavController(), rememberDrawerState(DrawerValue.Closed)) {
		Column() {}
	}
}