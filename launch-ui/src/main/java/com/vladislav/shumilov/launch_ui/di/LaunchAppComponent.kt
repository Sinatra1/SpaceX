package com.vladislav.shumilov.launch_ui.di

interface LaunchAppComponent {
    fun plusLaunchComponent(launchModule: LaunchModule): LaunchComponent
}