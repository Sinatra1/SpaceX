package com.vladislav.shumilov.launch_ui.di

import com.vladislav.shumilov.launch_ui.ui.LaunchesListFragment
import dagger.Subcomponent

@Subcomponent(modules = [LaunchModule::class])
@LaunchScope
interface LaunchComponent {

    fun inject(fragment: LaunchesListFragment)
}