package com.hust.cartracking.features.auth.presentation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hust.cartracking.R
import com.hust.cartracking.core.ui.navigation.Screens
import com.hust.cartracking.core.ui.navigation.go

@Composable
fun SplashScreen(navController: NavHostController) {
	val logoComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.car_tracking))
	val loadingComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
	
	val scale = remember {
		Animatable(0f)
	}
	
	val progress by animateLottieCompositionAsState(
		composition = loadingComposition,
	)
	
	LaunchedEffect(key1 = true) {
		scale.animateTo(
			targetValue = 1f,
			animationSpec = tween(
				delayMillis = 500,
				easing = { OvershootInterpolator(2f).getInterpolation(it) }
			)
		)
	}
	
	LaunchedEffect(key1 = progress) {
		if (progress >= 1f) {
			//! Go to Login
			navController.go(Screens.LoginScreen.route)
		}
	}
	
	Column(
		modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Spacer(modifier = Modifier.weight(1f))
		
		LottieAnimation(
			composition = logoComposition,
			modifier = Modifier
				.size(200.dp)
				.scale(scale.value),
			iterations = LottieConstants.IterateForever
		)
		Text(
			text = stringResource(id = R.string.app_name),
			modifier = Modifier.scale(scale.value),
			style = MaterialTheme.typography.headlineLarge
		)
		
		Spacer(modifier = Modifier.weight(2f))
		
		LottieAnimation(
			composition = loadingComposition,
			modifier = Modifier.size(100.dp),
			iterations = LottieConstants.IterateForever,
		)
		Spacer(modifier = Modifier.weight(1f))
	}
}