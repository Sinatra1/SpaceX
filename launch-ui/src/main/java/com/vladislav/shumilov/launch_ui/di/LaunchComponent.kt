package com.vladislav.shumilov.launch_ui.di

import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_ui.ui.detail.LaunchDetailFragment
import com.vladislav.shumilov.launch_ui.ui.list.LaunchesListFragment
import com.vladislav.shumilov.rocket_ui.di.RocketModule
import com.vladislav.shumilov.ship_ui.di.ShipModule
import com.vladislav.shumiov.mission_ui.di.MissionModule
import dagger.Subcomponent

@Subcomponent(modules = [LaunchModule::class, MissionModule::class, RocketModule::class, ShipModule::class])
@FragmentScope
interface LaunchComponent {

    fun inject(fragment: LaunchesListFragment)

    fun inject(fragment: LaunchDetailFragment)


}