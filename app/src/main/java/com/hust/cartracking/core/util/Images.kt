package com.hust.cartracking.core.util

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hust.cartracking.R

/********************
 * @Author: Tiiee
 * @Date: 6/28/2023
 * @Time: 3:34 PM
 ********************/

@Composable
fun NetworkImage(
	url: String,
	contentScale: ContentScale = ContentScale.Crop,
	@SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
	AsyncImage(
		model = ImageRequest.Builder(LocalContext.current)
			.data(url)
			.crossfade(true)
			.build(),
		placeholder = painterResource(R.drawable.placeholder),
		error = painterResource(R.drawable.img_error),
		contentDescription = url,
		contentScale = contentScale,
		modifier = modifier,
	)
}

@Composable
fun LocalImage(
	@DrawableRes id: Int,
	contentScale: ContentScale = ContentScale.Crop,
	@SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
	Image(
		painter = painterResource(id),
		contentDescription = id.toString(),
		contentScale = contentScale,
		modifier = modifier
	)
}