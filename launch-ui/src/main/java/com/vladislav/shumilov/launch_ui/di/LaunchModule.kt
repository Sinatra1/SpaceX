package com.vladislav.shumilov.launch_ui.di

import com.vladislav.shumilov.launch_data.LaunchScope
import com.vladislav.shumilov.launch_data.api.LaunchApi
import com.vladislav.shumilov.launch_data.repository.LaunchRemoteRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
@LaunchScope
class LaunchModule {

    @Provides
    @LaunchScope
    fun provideLaunchRemoteRepository(launchApi: LaunchApi) = LaunchRemoteRepository(launchApi)

    @Provides
    @LaunchScope
    fun provideLaunchApi(retrofit: Retrofit) = retrofit.create<LaunchApi>(LaunchApi::class.java)
}