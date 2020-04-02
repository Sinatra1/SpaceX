package com.vladislav.shumilov.launch_ui.di

import com.vladislav.shumilov.rocket_ui.di.RocketModule
import com.vladislav.shumiov.mission_ui.di.MissionModule

interface LaunchAppComponent {
    fun plusLaunchComponent(
        launchModule: LaunchModule,
        missionModule: MissionModule,
        rocketModule: RocketModule
    ): LaunchComponent
}