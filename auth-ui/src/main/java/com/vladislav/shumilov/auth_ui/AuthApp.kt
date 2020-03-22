package com.vladislav.shumilov.auth_ui

import android.app.Activity
import androidx.fragment.app.Fragment
import com.vladislav.shumilov.auth_ui.di.AuthComponent

fun Activity.app() = this.application as AuthApp
fun Fragment.app() = this.activity?.app()

interface AuthApp {

    fun createAuthComponent(): AuthComponent

    fun clearAuthComponent()
}