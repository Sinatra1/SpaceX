package com.vladislav.shumilov.launch_ui

import android.app.Activity
import androidx.fragment.app.Fragment
import com.vladislav.shumilov.launch_ui.di.LaunchComponent

fun Activity.app() = this.application as AuthApp
fun Fragment.app() = this.activity?.app()

interface AuthApp {

    fun createAuthComponent(): LaunchComponent

    fun clearAuthComponent()
}