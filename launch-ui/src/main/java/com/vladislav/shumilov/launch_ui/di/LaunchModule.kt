package com.vladislav.shumilov.launch_ui.di

import com.example.rocket_data.repository.RocketRemoteRepositoryImpl
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_data.api.LaunchApi
import com.vladislav.shumilov.launch_data.repository.LaunchFailureDetailsRemoteRepositoryImpl
import com.vladislav.shumilov.launch_data.repository.LaunchRemoteRepositoryImpl
import com.vladislav.shumilov.launch_data.repository.LaunchSiteRemoteRepositoryImpl
import com.vladislav.shumilov.launch_data.repository.LinksRemoteRepositoryImpl
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
    ): LaunchRemoteRepositoryImpl = LaunchRemoteRepositoryImpl(
        launchApi,
        rocketRemoteRepository,
        launchSiteRemoteRepository,
        launchFailureDetailsRemoteRepository,
        linksRemoteRepository
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
}