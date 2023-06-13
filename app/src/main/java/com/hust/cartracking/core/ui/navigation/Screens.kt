package com.hust.cartracking.core.ui.navigation

sealed class Screens(val route: String) {
	object SplashScreen : Screens("splash_screen")
	object LoginScreen : Screens("login_screen")
	object HomeScreen : Screens("monitor_screen") // Giám sát
	
	// Lịch sử
	object HistoryByCarScreen : Screens("history_by_car_screen") // Lịch sử xe
	object HistoryByScheduleScreen : Screens("history_by_schedule_screen") // Lịch sử tuyến
	
	// Quản lý tài khoản
	object AccountScreen : Screens("account_screen") // Tài khoản
	object PermissionScreen : Screens("permission_screen") // Danh sách quyền
	object ScopeScreen : Screens("scope_screen") // Danh sách nhóm quyền
	object ScopePermissionScreen : Screens("scope_permission_screen") // Quyền - nhóm quyền
	object ScopeDivisionScreen : Screens("scope_division_screen") // Phân quyền tài khoản
	
	// Quản lý đơn vị
	object GroupManagementScreen :
		Screens("group_management_screen") // Danh sách chi nhánh - đơn vị
	
	object UserUnitScreen : Screens("user_unit_screen") // Phân công đơn vị
	
	// Vận hành tuyến
	object PointScreen : Screens("point_screen") // Quản lý điểm giao dịch
	object ScheduleSampleScreen : Screens("schedule_sample_screen") // Quản lý tuyến mẫu
	object ScheduleScreen : Screens("schedule_screen") // Phân công tuyến
	
	// Quản lý phương tiện/thiết bị
	object RfidScreen : Screens("rfid_screen") // Thẻ RFID
	object DeviceScreen : Screens("device_screen") // Thiết bị lắp trên xe
	object VehicleScreen : Screens("vehicle_screen") // Xe ô tô
	object VehicleMaintenanceScreen :
		Screens("vehicle_maintenance_screen") // Bảo dưỡng/Bảo hiểm/Đăng kiểm/Thay dầu
	
	// Quản lý cán bộ
	object DriverScreen : Screens("driver_screen") // Lái xe
	object OwnerScreen : Screens("owner_screen") // Chủ hàng/thủ quỹ ATM
	object AtmTechnicianScreen : Screens("atm_technician_screen") // Kĩ thuật viên ATM
	object EscortScreen : Screens("escort_screen") // Cán bộ áp tải
	object RecipientScreen : Screens("recipient_screen") // Cán bộ nhận cảnh báo
	
	object SettingScreen : Screens("setting_screen") // Chức năng cài đặt
	object PasswordScreen : Screens("password_screen") // Chức năng đổi mật khẩu
	object EmptyScreen : Screens("")
	
	fun withArgs(vararg args: String): String {
		return buildString {
			append(route)
			args.forEach { arg -> append("/$arg") }
		}
	}
	
	companion object {
		fun getScreenName(route: String?): String {
			return when (route) {
				HomeScreen.route -> "Giám sát"
				HistoryByCarScreen.route -> "Lịch sử xe"
				HistoryByScheduleScreen.route -> "Lịch sử tuyến"
				AccountScreen.route -> "Tài khoản"
				PermissionScreen.route -> "Danh sách quyền"
				ScopeScreen.route -> " Danh sách nhóm quyền"
				ScopePermissionScreen.route -> "Quyền - nhóm quyền"
				ScopeDivisionScreen.route -> "Phân quyền tài khoản"
				GroupManagementScreen.route -> "Danh sách chi nhánh - đơn vị"
				UserUnitScreen.route -> "Phân công đơn vị"
				PointScreen.route -> "Quản lý điểm giao dịch"
				ScheduleSampleScreen.route -> "Quản lý tuyến mẫu"
				ScheduleScreen.route -> "Phân công tuyến"
				RfidScreen.route -> "Thẻ RFID"
				DeviceScreen.route -> "Thiết bị lắp trên xe"
				VehicleScreen.route -> "Xe ô tô"
				VehicleMaintenanceScreen.route -> "Bảo dưỡng/Bảo hiểm/Đăng kiểm/Thay dầu"
				DriverScreen.route -> "Lái xe"
				OwnerScreen.route -> "Chủ hàng/thủ quỹ ATM"
				AtmTechnicianScreen.route -> "Kĩ thuật viên ATM"
				EscortScreen.route -> "Cán bộ áp tải"
				RecipientScreen.route -> "Cán bộ nhận cảnh báo"
				SettingScreen.route -> "Cài đặt"
				PasswordScreen.route -> "Đổi mật khẩu"
				else -> ""
			}
		}
	}
}