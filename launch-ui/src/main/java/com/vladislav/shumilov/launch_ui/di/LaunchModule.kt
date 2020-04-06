package com.vladislav.shumilov.launch_ui.di

import com.example.rocket_data.database.RocketDao
import com.example.rocket_data.repository.RocketRemoteRepositoryImpl
import com.vladislav.shumilov.app_data.database.AppDatabase
import com.vladislav.shumilov.core_data.ApplicationScope
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_data.api.LaunchApi
import com.vladislav.shumilov.launch_data.database.*
import com.vladislav.shumilov.launch_data.repository.*
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
        rocketRemoteRepository: RocketRemoteRepositoryImpl,
        launchSiteRemoteRepository: LaunchSiteRemoteRepositoryImpl,
        launchFailureDetailsRemoteRepository: LaunchFailureDetailsRemoteRepositoryImpl,
        linksRemoteRepository: LinksRemoteRepositoryImpl
    ) = LaunchRemoteRepositoryImpl(
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
        rocketDao: RocketDao,
        shipDao: ShipDao,
        launchSiteDao: LaunchSiteDao,
        launchFailureDetailsDao: LaunchFailureDetailsDao,
        linksDao: LinksDao,
        launchToMissionDao: LaunchToMissionDao,
        launchToShipDao: LaunchToShipDao
    ) =
        LaunchLocalRepositoryImpl(
            launchDao,
            missionDao,
            rocketDao,
            shipDao,
            launchSiteDao,
            launchFailureDetailsDao,
            linksDao,
            launchToMissionDao,
            launchToShipDao
        )

    @Provides
    @FragmentScope
    fun provideLaunchSiteRemoteRepository() = LaunchSiteRemoteRepositoryImpl()

    @Provides
    @FragmentScope
    fun provideLaunchFailureDetailsRemoteRepository() = LaunchFailureDetailsRemoteRepositoryImpl()

    @Provides
    @FragmentScope
    fun provideLinksRemoteRepository() = LinksRemoteRepositoryImpl()

    @Provides
    @FragmentScope
    fun provideLaunchApi(retrofit: Retrofit) = retrofit.create<LaunchApi>(LaunchApi::class.java)

    @Provides
    @FragmentScope
    fun provideLaunchDao(@ApplicationScope appDatabase: AppDatabase): LaunchDao =
        appDatabase.getLaunchDao()

    @Provides
    @FragmentScope
    fun provideLaunchSiteDao(@ApplicationScope appDatabase: AppDatabase): LaunchSiteDao =
        appDatabase.getLaunchSiteDao()

    @Provides
    @FragmentScope
    fun provideLaunchFailureDetailsDao(@ApplicationScope appDatabase: AppDatabase): LaunchFailureDetailsDao =
        appDatabase.getLaunchFailureDetailsDao()

    @Provides
    @FragmentScope
    fun provideLinksDao(@ApplicationScope appDatabase: AppDatabase): LinksDao =
        appDatabase.getLinksDao()

    @Provides
    @FragmentScope
    fun provideLaunchToMissionDao(@ApplicationScope appDatabase: AppDatabase): LaunchToMissionDao =
        appDatabase.getLaunchToMissionDao()

    @Provides
    @FragmentScope
    fun provideLaunchToShipDao(@ApplicationScope appDatabase: AppDatabase): LaunchToShipDao =
        appDatabase.getLaunchToShipDao()


}