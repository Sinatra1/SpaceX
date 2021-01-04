package com.vladislav.shumilov.mytwitter.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vladislav.shumilov.mytwitter.AppActivity

@Module
internal abstract class ActivityBindersModule {

    @ContributesAndroidInjector(modules = [AppRouterModule::class])
    abstract fun contributeAppActivity(): AppActivity
}