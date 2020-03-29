package com.vladislav.shumilov.launch_ui.di

import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_ui.ui.LaunchesListFragment
import com.vladislav.shumilov.rocket_ui.di.RocketModule
import dagger.Subcomponent

@Subcomponent(modules = [LaunchModule::class, RocketModule::class])
@FragmentScope
interface LaunchComponent {

    fun inject(fragment: LaunchesListFragment)
}