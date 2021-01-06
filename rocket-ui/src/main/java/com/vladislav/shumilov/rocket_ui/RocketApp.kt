package com.vladislav.shumilov.rocket_ui

import com.vladislav.shumilov.rocket_ui.di.RocketComponent

interface RocketApp {

    fun createRocketComponent(): RocketComponent

    fun clearRocketComponent()
}