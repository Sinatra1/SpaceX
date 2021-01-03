package com.vladislav.shumilov.launch_ui.di

import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_ui.ui.detail.LaunchDetailFragment
import com.vladislav.shumilov.launch_ui.ui.list.LaunchesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentLaunchInjectors {

    @FragmentScope
    @ContributesAndroidInjector(modules = [LaunchDetailModule::class])
    abstract fun provideLaunchDetailFragment(): LaunchDetailFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [LaunchDetailModule::class])
    abstract fun provideLaunchesListFragment(): LaunchesListFragment
}

internal const val LAUNCH_DETAIL_ID = "launch_detail_id"