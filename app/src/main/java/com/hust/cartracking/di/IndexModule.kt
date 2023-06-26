package com.hust.cartracking.di

import com.hust.cartracking.core.data.local.AppCache
import com.hust.cartracking.core.util.Constants
import com.hust.cartracking.features.index.data.remote.IndexAPI
import com.hust.cartracking.features.index.data.repository.IndexRepositoryImpl
import com.hust.cartracking.features.index.domain.repository.IndexRepository
import com.hust.cartracking.features.index.domain.usecase.GetMonitorIndex
import com.hust.cartracking.features.index.domain.usecase.IndexUC
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
 * @Date: 6/25/2023
 * @Time: 5:56 PM
 ********************/

@Module
@InstallIn(SingletonComponent::class)
object IndexModule {
	
	@Provides
	@Singleton
	@Named("IndexOkHttpClient")
	fun provideIndexOkHttpClient(
		okHttpClientBuilder: OkHttpClient.Builder,
		appCacheManager: AppCache,
	): OkHttpClient {
		return okHttpClientBuilder
			.addInterceptor {
				val accessToken = appCacheManager.readValue(Constants.ACCESS_TOKEN_VALUE_KEY)
				val modifiedRequest = it.request().newBuilder()
					.header(
						"cookie",
						"x-access-token=$accessToken"
					)
					.build()
				it.proceed(modifiedRequest)
			}
			.build()
	}
	
	@Provides
	@Singleton
	fun provideIndexApi(
		retrofitBuilder: Retrofit.Builder,
		@Named("IndexOkHttpClient") client: OkHttpClient
	): IndexAPI {
		return retrofitBuilder
			.client(client)
			.build()
			.create(IndexAPI::class.java)
	}
	
	@Provides
	@Singleton
	fun provideIndexRepository(
		api: IndexAPI,
		appCacheManager: AppCache
	): IndexRepository {
		return IndexRepositoryImpl(api, appCacheManager)
	}
	
	@Provides
	@Singleton
	fun provideIndexUC(repository: IndexRepository): IndexUC {
		return IndexUC(
			getMonitorIndex = GetMonitorIndex(repository)
		)
	}
	
}
