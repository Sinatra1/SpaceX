package com.vladislav.shumilov.launch_ui.di

import com.vladislav.shumilov.launch_ui.ui.detail.LaunchDetailFragment
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
internal class LaunchDetailModule {
    @Provides
    @Named(LAUNCH_DETAIL_ID)
    fun provideLaunchDetailId(fragment: LaunchDetailFragment) = fragment.getLaunchDetailId()
}