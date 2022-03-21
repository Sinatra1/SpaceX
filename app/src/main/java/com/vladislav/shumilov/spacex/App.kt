package com.vladislav.shumilov.spacex

import com.vladislav.shumilov.core_ui.DaggerCoreComponent
import com.vladislav.shumilov.core_ui.injection.modules.AppModule
import com.vladislav.shumilov.design_ui.di.DaggerDesignComponent
import com.vladislav.shumilov.launch_ui.LaunchApp
import com.vladislav.shumilov.launch_ui.di.LaunchComponent
import com.vladislav.shumilov.spacex.di.AppComponent
import com.vladislav.shumilov.spacex.di.DaggerAppComponent
import com.vladislav.shumilov.rocket_ui.RocketApp
import com.vladislav.shumilov.rocket_ui.di.RocketComponent
import com.vladislav.shumilov.spacex.BuildConfig
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import kotlin.properties.Delegates

class App : DaggerApplication(), LaunchApp, RocketApp {

    private var appComponent: AppComponent by Delegates.notNull()
    private var launchComponent: LaunchComponent? = null
    private var rocketComponent: RocketComponent? = null

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

    override fun createRocketComponent(): RocketComponent {
        if (rocketComponent == null) {
            rocketComponent =
                appComponent.plusRocketComponent()
        }

        return rocketComponent!!
    }

    override fun clearRocketComponent() {
        rocketComponent = null
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val coreComponent = DaggerCoreComponent.factory().create(AppModule(this))
        val designComponent = DaggerDesignComponent.create()
        appComponent =
            DaggerAppComponent.factory().create(coreComponent, designComponent)

        return appComponent
    }

    private fun initLogs() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
