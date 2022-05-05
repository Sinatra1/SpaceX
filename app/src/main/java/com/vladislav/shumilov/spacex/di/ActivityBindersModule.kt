package com.vladislav.shumilov.spacex.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.vladislav.shumilov.spacex.AppActivity

@Module
internal abstract class ActivityBindersModule {

    @ContributesAndroidInjector
    abstract fun contributeAppActivity(): AppActivity
}
