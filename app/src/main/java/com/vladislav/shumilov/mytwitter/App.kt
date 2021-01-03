package com.vladislav.shumilov.mytwitter

import android.app.Application
import com.vladislav.shumilov.core_data.ApplicationScope
import com.vladislav.shumilov.launch_ui.di.LaunchComponent
import com.vladislav.shumilov.core_ui.injection.modules.AppModule
import com.vladislav.shumilov.launch_ui.LaunchApp
import com.vladislav.shumilov.mytwitter.di.AppComponent
import com.vladislav.shumilov.mytwitter.di.DaggerAppComponent
import com.vladislav.shumilov.mytwitter.di.DaggerAppComponent.factory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.internal.MapFactory.builder
import timber.log.Timber
import vladislav.shumilov.mytwitter.BuildConfig
import javax.inject.Inject
import kotlin.properties.Delegates

@ApplicationScope
class App : Application(), LaunchApp, HasAndroidInjector {

    private var appComponent: AppComponent by Delegates.notNull()

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = childFragmentInjector

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(AppModule(this))
        appComponent.inject(this)

        initLogs()
    }

    private fun initLogs() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}