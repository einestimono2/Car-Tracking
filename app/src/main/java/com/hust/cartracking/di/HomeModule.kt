package com.hust.cartracking.di

import com.hust.cartracking.core.data.local.AppCache
import com.hust.cartracking.core.util.Constants
import com.hust.cartracking.features.home.data.remote.HomeAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/********************
 * @Author: Tiiee
 * @Date: 5/29/2023
 * @Time: 10:12 PM
 ********************/

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
	
	@Provides
	@Singleton
	@Named("HomeOkHttpClient")
	fun provideHomeOkHttpClient(
		okHttpClientBuilder: OkHttpClient.Builder,
		appCacheManager: AppCache,
	): OkHttpClient {
		return okHttpClientBuilder
			.addInterceptor {
				val accessToken = appCacheManager.readValue(Constants.ACCESS_TOKEN_KEY)
				val modifiedRequest = it.request().newBuilder()
					.addHeader("Cookie", "x-access-token=$accessToken")
					.build()
				it.proceed(modifiedRequest)
			}
			.build()
	}
	
	@Provides
	@Singleton
	fun provideHomeApi(
		retrofitBuilder: Retrofit.Builder,
		@Named("HomeOkHttpClient") client: OkHttpClient
	): HomeAPI {
		return retrofitBuilder
			.client(client)
			.build()
			.create(HomeAPI::class.java)
	}
}