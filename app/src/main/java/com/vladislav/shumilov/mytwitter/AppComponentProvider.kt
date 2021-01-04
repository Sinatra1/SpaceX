package com.vladislav.shumilov.mytwitter

import android.app.Activity
import androidx.fragment.app.Fragment
import com.vladislav.shumilov.mytwitter.di.AppComponent

internal fun Activity.app() = this.application as AppComponentProvider
internal fun Fragment.app() = this.activity?.app()

interface AppComponentProvider {

    fun appComponent(): AppComponent
}