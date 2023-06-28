package com.hust.cartracking.features.home.presentation.monitor.components

import android.content.Context
import android.content.res.Configuration
import android.location.Geocoder
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hust.cartracking.core.util.extensions.Border
import com.hust.cartracking.core.util.extensions.TimeFormat
import com.hust.cartracking.core.util.extensions.border
import com.hust.cartracking.core.util.extensions.convertTime
import com.hust.cartracking.core.util.extensions.roundTo2DecimalPlaces
import com.hust.cartracking.core.util.extensions.widthRatio
import com.hust.cartracking.core.util.mapCarStatus
import com.hust.cartracking.core.util.mapCarType
import com.hust.cartracking.features.home.domain.model.CarOnline
import timber.log.Timber
import java.util.Locale

/********************
 * @Author: Tiiee
 * @Date: 6/18/2023
 * @Time: 4:24 PM
 ********************/

@Composable
fun CustomMarkerInfoWindow(car: CarOnline, context: Context) {
	val geo = Geocoder(context, Locale("vi"))
	
	Box(
		modifier = Modifier
			.clip(RoundedCornerShape(8.dp))
			.wrapContentHeight()
			.widthRatio(0.75f)
			.background(Color.White)
			.padding(8.dp)
	) {
		Column(
			modifier = Modifier
				.wrapContentHeight()
				.fillMaxWidth()
		) {
			Cell(
				"Tọa độ",
				"G(${car.gpsLat.roundTo2DecimalPlaces}, ${car.gpsLon.roundTo2DecimalPlaces})" +
					"\nN(${car.networkLat.roundTo2DecimalPlaces}, ${car.networkLon.roundTo2DecimalPlaces})"
			)
			
			geo.getFromLocation(car.gpsLat, car.gpsLon, 1)?.get(0)?.let {
				Timber.v(it.toString())
				Cell(
					"Địa chỉ",
					it.getAddressLine(0)
				)
			}
			
			Cell(
				"Đơn vị",
				car.unitName
			)
			Cell(
				"Loại xe",
				mapCarType(car.carType)
			)
			Cell(
				"Biển số xe",
				car.licensePlate
			)
			Cell(
				"Lái chính",
				car.driverName ?: ""
			)
			Cell(
				"Trạng thái",
				"${mapCarStatus(car.stateName)} (${car.deviceTime.convertTime(TimeFormat.DEFAULT)})"
			)
			Cell(
				"Vận tốc",
				"${car.gpsVelocity}"
			)
			Cell(
				"Khoang két",
				if (car.strongBoxOpen) "Mở" else "Đóng"
			)
			Cell(
				"Phiên bản",
				car.appVersion,
				true
			)
			
		}
	}
}

@Composable
fun Cell(title: String, value: String, borderBorder: Boolean = false) {
	Row(
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.height(IntrinsicSize.Min)
			.border(
				start = Border(1.dp, Color.Blue),
				top = Border(1.dp, Color.Blue),
				end = Border(1.dp, Color.Blue),
				bottom = if(borderBorder) Border(1.dp, Color.Blue) else null,
			),
	) {
		Text(
			text = title,
			modifier = Modifier
				.weight(1f)
				.padding(6.dp)
		)
		Box(
			modifier = Modifier
				.fillMaxHeight()
				.width(1.dp)
				.background(Color.Blue)
		)
		Text(
			text = value,
			modifier = Modifier
				.weight(2f)
				.padding(6.dp)
		)
	}
}

//@Preview(
//	showBackground = true, backgroundColor = 256 * 256 * 256, // R * G * B
//	showSystemUi = true, name = "CustomMarkerInfoWindow", uiMode = Configuration.UI_MODE_TYPE_NORMAL
//)
//@Composable
//fun AppDrawerPreview() {
//	CustomMarkerInfoWindow(
//		CarOnline(
//			carId = 78,
//			licensePlate = "29KT-000.46",
//			unitId = 32,
//			unitName = "Hà Nội - Đã chuyển giao",
//			carStatus = 2,
//			strongBoxOpen = false,
//			engineOn = false,
//			isSos = true,
//			isGpsLost = false,
//			gpsLat = 21.27784047,
//			gpsLon = 103.97846162,
//			networkLat = 21.27784047,
//			networkLon = 103.97846162,
//			deviceTime = "2022-12-28T20:03:42",
//			firstCamPo = 1,
//			firstCamRotation = null,
//			secondCamRotation = null,
//			gpsVelocity = 0,
//			cellId = 0,
//			lacId = 0,
//			rfidString = "FF017F000000007EFFFFFFFFFF017F000000007EFFFFFFFFFF017F000000007EFFFFFFFFFF017F000000007EFFFFFFFFFF017F000000007EFFFFFFFFFF017F000000007EFFFFFFFFFF017F000000007EFFFFFFFFFF017F000000007EFFFFFFFFFF017F000000007EFFFFFFFFFF017F000000007EFFFFFFFFFF017F000000007",
//			appVersion = "2022122002_mb_hub3.0_onoff_cam",
//			smallIConState = "../../img/state/sos-16.png",
//			bigIconState = "../../img/state/sos.png",
//			stateName = "SOS",
//			carType = 1,
//			driverId = 126,
//			driverName = "Phạm Ngọc Hưng",
//			userId = 1,
//			isJamming = true,
//			isSpoofing = true,
//		)
//	)
//}