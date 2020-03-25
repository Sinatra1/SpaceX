package com.vladislav.shumilov.launch_ui

import android.app.Activity
import androidx.fragment.app.Fragment
import com.vladislav.shumilov.launch_ui.di.LaunchComponent

fun Activity.app() = this.application as LaunchApp
fun Fragment.app() = this.activity?.app()

interface LaunchApp {

    fun createLaunchComponent(): LaunchComponent

    fun clearLaunchComponent()
}