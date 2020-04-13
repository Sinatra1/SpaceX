package com.vladislav.shumilov.launch_ui.di

import com.example.rocket_data.database.RocketDao
import com.example.rocket_domain.repository.RocketLocalRepositoryAlias
import com.example.rocket_domain.repository.RocketRemoteRepositoryAlias
import com.vladislav.shumilov.app_data.database.AppDatabase
import com.vladislav.shumilov.core_data.ApplicationScope
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_data.api.LaunchApi
import com.vladislav.shumilov.launch_data.database.*
import com.vladislav.shumilov.launch_data.repository.*
import com.vladislav.shumilov.launch_domain.repository.LaunchFailureDetailsRemoteRepositoryAlias
import com.vladislav.shumilov.launch_domain.repository.LaunchSiteRemoteRepositoryAlias
import com.vladislav.shumilov.launch_domain.repository.LinksRemoteRepositoryAlias
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
        rocketRemoteRepository: RocketRemoteRepositoryAlias,
        launchSiteRemoteRepository: LaunchSiteRemoteRepositoryAlias,
        launchFailureDetailsRemoteRepository: LaunchFailureDetailsRemoteRepositoryAlias,
        linksRemoteRepository: LinksRemoteRepositoryAlias
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
        rocketLocalRepository: RocketLocalRepositoryAlias,
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
            rocketLocalRepository,
            shipDao,
            launchSiteDao,
            launchFailureDetailsDao,
            linksDao,
            launchToMissionDao,
            launchToShipDao
        )

    @Suppress("UNCHECKED_CAST")
    @Provides
    @FragmentScope
    fun provideLaunchSiteRemoteRepository() =
        LaunchSiteRemoteRepositoryImpl() as LaunchSiteRemoteRepositoryAlias

    @Suppress("UNCHECKED_CAST")
    @Provides
    @FragmentScope
    fun provideLaunchFailureDetailsRemoteRepository() =
        LaunchFailureDetailsRemoteRepositoryImpl() as LaunchFailureDetailsRemoteRepositoryAlias

    @Suppress("UNCHECKED_CAST")
    @Provides
    @FragmentScope
    fun provideLinksRemoteRepository() = LinksRemoteRepositoryImpl() as LinksRemoteRepositoryAlias

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