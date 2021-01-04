package com.vladislav.shumilov.mytwitter

import android.widget.Toast
import vladislav.shumilov.mytwitter.AppActivity

internal class AppRouter(private val appActivity: AppActivity) {

    fun showActivity() {
        Toast.makeText(appActivity.applicationContext, appActivity.javaClass.canonicalName, Toast.LENGTH_LONG).show()
    }
}