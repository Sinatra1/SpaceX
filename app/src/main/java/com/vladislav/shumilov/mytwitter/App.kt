package com.vladislav.shumilov.mytwitter

import android.app.Application
import com.vladislav.shumilov.auth_ui.AuthApp
import com.vladislav.shumilov.auth_ui.di.AuthComponent
import com.vladislav.shumilov.auth_ui.di.AuthModule
import com.vladislav.shumilov.core_ui.injection.modules.AppModule
import com.vladislav.shumilov.mytwitter.components.AppComponent
import com.vladislav.shumilov.mytwitter.components.DaggerAppComponent
import timber.log.Timber
import vladislav.shumilov.mytwitter.BuildConfig
import kotlin.properties.Delegates

class App : Application(), AuthApp {

    private var appComponent: AppComponent by Delegates.notNull()
    private var authComponent : AuthComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

        initLogs()
    }

    override fun createAuthComponent(): AuthComponent {
        if (authComponent == null) {
            authComponent = appComponent.plusAuthComponent(AuthModule())
        }

        return authComponent!!
    }

    override fun clearAuthComponent() {
        authComponent = null
    }

    private fun initLogs() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}