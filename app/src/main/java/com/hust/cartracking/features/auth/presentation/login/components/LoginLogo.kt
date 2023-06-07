package com.hust.cartracking.features.auth.presentation.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hust.cartracking.R
import com.hust.cartracking.core.util.extensions.heightRatio
import com.hust.cartracking.core.util.extensions.widthRatio

/********************
 * @Author: Tiiee
 * @Date: 5/29/2023
 * @Time: 10:36 AM
 ********************/

@Composable
fun LoginLogo() {
	Image(
		modifier = Modifier
			.widthRatio(0.3f)
			.clip(RoundedCornerShape(8.dp)),
		painter = painterResource(id = R.drawable.app_logo),
		contentDescription = "LoginLogo",
		contentScale = ContentScale.Fit
	)
	Spacer(modifier = Modifier.heightRatio(0.015f))
	Text(
		text = stringResource(id = R.string.app_name),
		style = MaterialTheme.typography.headlineLarge
	)
	Spacer(modifier = Modifier.height(4.dp))
	Text(
		text = "Đăng nhập để tiếp tục!",
		style = MaterialTheme.typography.bodyMedium
	)
}