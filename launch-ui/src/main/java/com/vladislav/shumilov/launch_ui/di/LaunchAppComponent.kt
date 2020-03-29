package com.vladislav.shumilov.launch_ui.di

import com.vladislav.shumilov.rocket_ui.di.RocketModule

interface LaunchAppComponent {
    fun plusLaunchComponent(launchModule: LaunchModule, rocketModule: RocketModule): LaunchComponent
}