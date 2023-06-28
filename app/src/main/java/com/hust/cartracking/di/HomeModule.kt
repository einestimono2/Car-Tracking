package com.hust.cartracking.di

import com.hust.cartracking.core.data.local.AppCache
import com.hust.cartracking.core.util.Constants
import com.hust.cartracking.features.home.data.remote.MonitorAPI
import com.hust.cartracking.features.home.data.repository.MonitorRepositoryImpl
import com.hust.cartracking.features.home.domain.repository.MonitorRepository
import com.hust.cartracking.features.home.domain.usecase.GetAllCarOnline
import com.hust.cartracking.features.home.domain.usecase.GetAllWarning
import com.hust.cartracking.features.home.domain.usecase.GetCarImage
import com.hust.cartracking.features.home.domain.usecase.GetCarInfo
import com.hust.cartracking.features.home.domain.usecase.GetLstRunningSchedule
import com.hust.cartracking.features.home.domain.usecase.GetLstUpcomingSchedule
import com.hust.cartracking.features.home.domain.usecase.GetPointShortDataByGroup
import com.hust.cartracking.features.home.domain.usecase.GetVehicleGroupByUser
import com.hust.cartracking.features.home.domain.usecase.MonitorUC
import com.hust.cartracking.features.index.domain.usecase.GetMonitorIndex
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
	@Named("MonitorOkHttpClient")
	fun provideMonitorOkHttpClient(
		okHttpClientBuilder: OkHttpClient.Builder,
		appCacheManager: AppCache,
		getMonitorIndex: GetMonitorIndex,
	): OkHttpClient {
		return okHttpClientBuilder
			.addInterceptor {
				val index = getMonitorIndex().data!!
				val accessToken = appCacheManager.readValue(Constants.ACCESS_TOKEN_VALUE_KEY)
				
				val modifiedRequest = it.request().newBuilder()
					.header(
						"Cookie",
						".AspNetCore.Antiforgery.dNc7gmZvK2I=$index;x-access-token=$accessToken"
					)
					.build()
				it.proceed(modifiedRequest)
			}
			.build()
	}
	
	@Provides
	@Singleton
	fun provideMonitorApi(
		retrofitBuilder: Retrofit.Builder,
		@Named("MonitorOkHttpClient") client: OkHttpClient
	): MonitorAPI {
		return retrofitBuilder
			.client(client)
			.build()
			.create(MonitorAPI::class.java)
	}
	
	@Provides
	@Singleton
	fun provideMonitorRepository(
		api: MonitorAPI,
	): MonitorRepository {
		return MonitorRepositoryImpl(api)
	}
	
	@Provides
	@Singleton
	fun provideMonitorUC(repository: MonitorRepository): MonitorUC {
		return MonitorUC(
			getVehicleGroupByUser = GetVehicleGroupByUser(repository),
			getAllCarOnline = GetAllCarOnline(repository),
			getAllWarning = GetAllWarning(repository),
			getLstRunningSchedule = GetLstRunningSchedule(repository),
			getLstUpcomingSchedule = GetLstUpcomingSchedule(repository),
			getPointShortDataByGroup = GetPointShortDataByGroup(repository),
			getCarInfo = GetCarInfo(repository),
			getCarImage = GetCarImage(repository),
		)
	}
}