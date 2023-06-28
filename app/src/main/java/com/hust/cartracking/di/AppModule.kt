package com.hust.cartracking.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hust.cartracking.BuildConfig
import com.hust.cartracking.core.data.local.AppCache
import com.hust.cartracking.core.data.local.AppCacheManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/********************
 * @Author: Tiiee
 * @Date: 5/25/2023
 * @Time: 4:43 PM
 ********************/

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
	
	@Provides
	@Singleton
	fun provideAppCacheManager(app: Application): AppCache {
		return AppCacheManager(app)
	}
	
	@Provides
	@Singleton
	fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
		return OkHttpClient.Builder()
			.addInterceptor(
				HttpLoggingInterceptor().apply {
					level = HttpLoggingInterceptor.Level.BODY
				}
			)
			.callTimeout(15, TimeUnit.SECONDS)
			.connectTimeout(15, TimeUnit.SECONDS)
			.writeTimeout(15, TimeUnit.SECONDS)
			.readTimeout(15, TimeUnit.SECONDS)
	}
	
	@Provides
	@Singleton
	fun provideRetrofitBuilder(gsonBuilder: Gson): Retrofit.Builder {
		return Retrofit.Builder()
			.baseUrl(BuildConfig.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create(gsonBuilder))
	}
	
	@Provides
	@Singleton
	fun provideGson(): Gson {
		return GsonBuilder().create()
	}
	
}