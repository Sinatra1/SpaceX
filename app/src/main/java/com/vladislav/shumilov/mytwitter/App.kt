package com.vladislav.shumilov.mytwitter

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.vladislav.shumilov.core_ui.injection.modules.AppModule
import com.vladislav.shumilov.mytwitter.components.AppComponent
import com.vladislav.shumilov.mytwitter.components.DaggerAppComponent
import timber.log.Timber
import vladislav.shumilov.mytwitter.BuildConfig
import kotlin.properties.Delegates


fun Activity.app() = this.application as App
fun Fragment.app() = this.activity?.app()

class App : Application() {

    var appComponent: AppComponent by Delegates.notNull()

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

        initLogs()
    }

    private fun initLogs() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}