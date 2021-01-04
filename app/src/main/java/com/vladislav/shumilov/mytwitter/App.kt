package com.vladislav.shumilov.mytwitter

import com.vladislav.shumilov.core_ui.injection.modules.AppModule
import com.vladislav.shumilov.launch_ui.LaunchApp
import com.vladislav.shumilov.launch_ui.di.LaunchComponent
import com.vladislav.shumilov.mytwitter.di.AppComponent
import com.vladislav.shumilov.mytwitter.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import vladislav.shumilov.mytwitter.BuildConfig
import kotlin.properties.Delegates


class App : DaggerApplication(), AppComponentProvider, LaunchApp {

    private var appComponent: AppComponent by Delegates.notNull()
    private var launchComponent: LaunchComponent? = null

    override fun onCreate() {
        super.onCreate()

        initLogs()
    }

    override fun createLaunchComponent(): LaunchComponent {
        if (launchComponent == null) {
            launchComponent =
                appComponent.plusLaunchComponent()
        }

        return launchComponent!!
    }

    override fun clearLaunchComponent() {
        launchComponent = null
    }

    override fun appComponent(): AppComponent = appComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent =
            DaggerAppComponent.builder().appModule(AppModule(this)).build()

        return appComponent
    }

    private fun initLogs() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}