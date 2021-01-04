package com.vladislav.shumilov.mytwitter.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vladislav.shumilov.mytwitter.AppActivity

@Module
internal abstract class ActivityBindersModule {

    @ContributesAndroidInjector
    abstract fun contributeAppActivity(): AppActivity
}