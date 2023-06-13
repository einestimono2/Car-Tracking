package com.hust.cartracking.core.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Atm
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CarRepair
import androidx.compose.material.icons.filled.Commute
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.LensBlur
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.Person3
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.Workspaces
import androidx.compose.ui.graphics.vector.ImageVector
import com.hust.cartracking.core.ui.navigation.Screens

/********************
 * @Author: Tiiee
 * @Date: 6/8/2023
 * @Time: 4:54 PM
 ********************/

data class MenuDrawerItem(
	val title: String,
	val icon: ImageVector,
	val route: String,
	val children: List<MenuDrawerItem>,
) {
	companion object {
		val items = listOf(
			MenuDrawerItem("Giám sát", Icons.Default.DirectionsCar, Screens.HomeScreen.route, emptyList()),
			
			MenuDrawerItem(
				"Lịch sử", Icons.Default.Timeline, Screens.EmptyScreen.route,
				listOf(
					MenuDrawerItem("Lịch sử xe", Icons.Default.CalendarMonth, Screens.HistoryByCarScreen.route, emptyList()),
					MenuDrawerItem("Lịch sử tuyến", Icons.Default.EventAvailable, Screens.HistoryByScheduleScreen.route, emptyList())
				)
			),
			
			MenuDrawerItem(
				"Quản lý đơn vị", Icons.Default.Workspaces, Screens.EmptyScreen.route,
				listOf(
					MenuDrawerItem(
						"Danh sách đơn vị",
						Icons.Default.AccountBalance,
						Screens.GroupManagementScreen.route,
						emptyList()
					),
					MenuDrawerItem("Phân công đơn vị", Icons.Default.LensBlur, Screens.UserUnitScreen.route, emptyList())
				)
			),
			
			MenuDrawerItem(
				"Vận hành tuyến", Icons.Default.Directions, Screens.EmptyScreen.route,
				listOf(
					MenuDrawerItem("Quản lý điểm giao dịch", Icons.Default.Place, Screens.PointScreen.route, emptyList()),
					MenuDrawerItem("Quản lý tuyến mẫu", Icons.Default.Route, Screens.ScheduleSampleScreen.route, emptyList()),
					MenuDrawerItem("Phân công tuyến", Icons.Default.Map, Screens.ScheduleScreen.route, emptyList())
				)
			),
			
			MenuDrawerItem(
				"Phương tiện/Thiết bị", Icons.Default.Commute, Screens.EmptyScreen.route,
				listOf(
					MenuDrawerItem("Thẻ RFID", Icons.Default.Style, Screens.RfidScreen.route, emptyList()),
					MenuDrawerItem("Thiết bị lắp trên xe", Icons.Default.Memory, Screens.DeviceScreen.route, emptyList()),
					MenuDrawerItem("Xe ô tô", Icons.Default.LocalShipping, Screens.VehicleScreen.route, emptyList()),
					MenuDrawerItem(
						"Bảo dưỡng/Bảo hiểm/Đăng kiểm/Thay dầu",
						Icons.Default.CarRepair,
						Screens.VehicleMaintenanceScreen.route,
						emptyList()
					),
				)
			),
			
			MenuDrawerItem(
				"Quản lý cán bộ", Icons.Default.People, Screens.EmptyScreen.route,
				listOf(
					MenuDrawerItem("Lái xe", Icons.Default.DirectionsBike, Screens.DriverScreen.route, emptyList()),
					MenuDrawerItem("Chủ hàng/Thủ quỹ ATM", Icons.Default.Atm, Screens.OwnerScreen.route, emptyList()),
					MenuDrawerItem(
						"Kỹ thuật viên ATM",
						Icons.Default.Person2,
						Screens.AtmTechnicianScreen.route,
						emptyList()
					),
					MenuDrawerItem(
						"Cán bộ áp tải",
						Icons.Default.Person3,
						Screens.EscortScreen.route,
						emptyList()
					),
					MenuDrawerItem(
						"Cán bộ nhận cảnh báo",
						Icons.Default.Person4,
						Screens.RecipientScreen.route,
						emptyList()
					),
				)
			),
			
			MenuDrawerItem(
				"Quản lý tài khoản", Icons.Default.AdminPanelSettings, Screens.EmptyScreen.route,
				listOf(
					MenuDrawerItem("Tài khoản", Icons.Default.Person, Screens.AccountScreen.route, emptyList()),
					MenuDrawerItem("Danh sách quyền", Icons.Default.Security, Screens.PermissionScreen.route, emptyList()),
					MenuDrawerItem("Danh sách nhóm quyền", Icons.Default.Bookmark, Screens.ScopeScreen.route, emptyList()),
					MenuDrawerItem("Quyền - Nhóm quyền", Icons.Default.Group, Screens.ScopePermissionScreen.route, emptyList()),
					MenuDrawerItem(
						"Phân quyền tài khoản",
						Icons.Default.ManageAccounts,
						Screens.ScopeDivisionScreen.route,
						emptyList()
					),
				)
			),
			
			MenuDrawerItem("Cài đặt", Icons.Default.Settings, Screens.SettingScreen.route, emptyList()),
			
			MenuDrawerItem("Đổi mật khẩu", Icons.Default.Key, Screens.PasswordScreen.route, emptyList()),
		)
	}
}