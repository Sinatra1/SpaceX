package com.vladislav.shumilov.launch_ui

import android.app.Activity
import androidx.fragment.app.Fragment
import com.vladislav.shumilov.launch_ui.di.LaunchComponent

internal fun Activity.app() = this.application as LaunchApp
internal fun Fragment.app() = this.activity?.app()

interface LaunchApp {

    fun createLaunchComponent(): LaunchComponent

    fun clearLaunchComponent()
}