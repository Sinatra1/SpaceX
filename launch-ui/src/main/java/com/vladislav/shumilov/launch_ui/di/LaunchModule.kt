package com.vladislav.shumilov.launch_ui.di

import com.example.rocket_domain.repository.RocketLocalRepository
import com.example.rocket_domain.repository.RocketRemoteRepository
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_data.api.LaunchApi
import com.vladislav.shumilov.launch_data.database.*
import com.vladislav.shumilov.launch_data.repository.*
import com.vladislav.shumilov.launch_domain.repository.*
import com.vladislav.shumilov.mission_data.database.MissionDao
import com.vladislav.shumilov.ship_data.database.ShipDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
@FragmentScope
class LaunchModule {

    @Provides
    @FragmentScope
    fun provideLaunchRemoteRepository(
        launchApi: LaunchApi,
        rocketRemoteRepository: RocketRemoteRepository,
        launchSiteRemoteRepository: LaunchSiteRemoteRepository,
        launchFailureDetailsRemoteRepository: LaunchFailureDetailsRemoteRepository,
        linksRemoteRepository: LinksRemoteRepository
    ): LaunchRemoteRepository =
        LaunchRemoteRepositoryImpl(
            launchApi,
            rocketRemoteRepository,
            launchSiteRemoteRepository,
            launchFailureDetailsRemoteRepository,
            linksRemoteRepository
        )

    @Provides
    @FragmentScope
    fun provideLaunchLocalRepository(
        launchDao: LaunchDao,
        missionDao: MissionDao,
        rocketLocalRepository: RocketLocalRepository,
        shipDao: ShipDao,
        launchSiteDao: LaunchSiteDao,
        launchFailureDetailsDao: LaunchFailureDetailsDao,
        linksDao: LinksDao,
        launchToMissionDao: LaunchToMissionDao,
        launchToShipDao: LaunchToShipDao
    ): LaunchLocalRepository =
        LaunchLocalRepositoryImpl(
            launchDao,
            missionDao,
            rocketLocalRepository,
            shipDao,
            launchSiteDao,
            launchFailureDetailsDao,
            linksDao,
            launchToMissionDao,
            launchToShipDao
        )

    @Provides
    @FragmentScope
    fun provideLaunchSiteRemoteRepository(): LaunchSiteRemoteRepository =
        LaunchSiteRemoteRepositoryImpl()

    @Provides
    @FragmentScope
    fun provideLaunchFailureDetailsRemoteRepository(): LaunchFailureDetailsRemoteRepository =
        LaunchFailureDetailsRemoteRepositoryImpl()


    @Provides
    @FragmentScope
    fun provideLinksRemoteRepository(): LinksRemoteRepository = LinksRemoteRepositoryImpl()

    @Provides
    @FragmentScope
    fun provideLaunchApi(retrofit: Retrofit): LaunchApi =
        retrofit.create<LaunchApi>(LaunchApi::class.java)
}