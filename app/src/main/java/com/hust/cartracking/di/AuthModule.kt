package com.hust.cartracking.di

import com.hust.cartracking.core.data.local.AppCache
import com.hust.cartracking.features.auth.data.remote.AuthAPI
import com.hust.cartracking.features.auth.data.repository.AuthRepositoryImpl
import com.hust.cartracking.features.auth.domain.repository.AuthRepository
import com.hust.cartracking.features.auth.domain.usecase.AuthUC
import com.hust.cartracking.features.auth.domain.usecase.Authenticate
import com.hust.cartracking.features.auth.domain.usecase.Login
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
 * @Date: 5/25/2023
 * @Time: 5:05 PM
 ********************/

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
	
	@Provides
	@Singleton
	@Named("AuthOkHttpClient")
	fun provideAuthOkHttpClient(
		okHttpClientBuilder: OkHttpClient.Builder
	): OkHttpClient {
		return okHttpClientBuilder.build()
	}
	
	@Provides
	@Singleton
	fun provideAuthApi(
		retrofitBuilder: Retrofit.Builder,
		@Named("AuthOkHttpClient") client: OkHttpClient
	): AuthAPI {
		return retrofitBuilder
			.client(client)
			.build()
			.create(AuthAPI::class.java)
	}
	
	@Provides
	@Singleton
	fun provideAuthRepository(
		api: AuthAPI,
		appCacheManager: AppCache
	): AuthRepository {
		return AuthRepositoryImpl(api, appCacheManager)
	}
	
	@Provides
	@Singleton
	fun provideAuthUC(repository: AuthRepository): AuthUC {
		return AuthUC(
			authenticate = Authenticate(repository),
			login = Login(repository)
		)
	}
	
}