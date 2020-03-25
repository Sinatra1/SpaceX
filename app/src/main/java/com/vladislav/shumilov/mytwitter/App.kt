package com.vladislav.shumilov.mytwitter

import android.app.Application
import com.vladislav.shumilov.launch_ui.di.LaunchComponent
import com.vladislav.shumilov.launch_ui.di.LaunchModule
import com.vladislav.shumilov.core_ui.injection.modules.AppModule
import com.vladislav.shumilov.launch_ui.LaunchApp
import com.vladislav.shumilov.mytwitter.components.AppComponent
import com.vladislav.shumilov.mytwitter.components.DaggerAppComponent
import timber.log.Timber
import vladislav.shumilov.mytwitter.BuildConfig
import kotlin.properties.Delegates

class App : Application(), LaunchApp {

    private var appComponent: AppComponent by Delegates.notNull()
    private var launchComponent : LaunchComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

        initLogs()
    }

    override fun createLaunchComponent(): LaunchComponent {
        if (launchComponent == null) {
            launchComponent = appComponent.plusLaunchComponent(LaunchModule())
        }

        return launchComponent!!
    }

    override fun clearLaunchComponent() {
        launchComponent = null
    }

    private fun initLogs() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}