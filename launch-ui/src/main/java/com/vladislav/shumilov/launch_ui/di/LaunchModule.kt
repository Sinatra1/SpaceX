package com.vladislav.shumilov.launch_ui.di

import com.example.rocket_data.database.RocketDao
import com.example.rocket_data.repository.RocketRemoteRepositoryImpl
import com.vladislav.shumilov.app_data.database.AppDatabase
import com.vladislav.shumilov.core_data.ApplicationScope
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_data.api.LaunchApi
import com.vladislav.shumilov.launch_data.database.LaunchDao
import com.vladislav.shumilov.launch_data.database.LaunchFailureDetailsDao
import com.vladislav.shumilov.launch_data.database.LaunchSiteDao
import com.vladislav.shumilov.launch_data.database.LinksDao
import com.vladislav.shumilov.launch_data.repository.*
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
        rocketDao: RocketDao,
        launchSiteDao: LaunchSiteDao,
        launchFailureDetailsDao: LaunchFailureDetailsDao,
        linksDao: LinksDao
    ) =
        LaunchLocalRepositoryImpl(
            launchDao,
            rocketDao,
            launchSiteDao,
            launchFailureDetailsDao,
            linksDao
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


}