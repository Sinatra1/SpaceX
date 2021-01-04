package com.vladislav.shumilov.mytwitter.di

import com.vladislav.shumilov.mytwitter.AppRouter
import dagger.Module
import dagger.Provides
import vladislav.shumilov.mytwitter.AppActivity

@Module
internal object AppRouterModule {

    @Provides
    @JvmStatic
    fun provideAppRouter(activity: AppActivity): AppRouter = AppRouter(activity)
}