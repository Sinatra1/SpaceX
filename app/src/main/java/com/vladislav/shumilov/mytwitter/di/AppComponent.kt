package com.vladislav.shumilov.mytwitter.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.google.gson.Gson
import com.vladislav.shumilov.app_data.database.AppDatabase
import com.vladislav.shumilov.core_data.ApplicationContext
import com.vladislav.shumilov.core_data.ApplicationScope
import com.vladislav.shumilov.core_ui.injection.modules.AppModule
import com.vladislav.shumilov.core_ui.injection.modules.GsonModule
import com.vladislav.shumilov.core_ui.injection.modules.HttpModule
import com.vladislav.shumilov.launch_ui.di.FragmentLaunchInjectors
import com.vladislav.shumilov.launch_ui.di.LaunchAppComponent
import com.vladislav.shumilov.launch_ui.di.LaunchComponent
import com.vladislav.shumilov.mytwitter.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(
    dependencies = [LaunchComponent::class],
    modules = [
    AppModule::class,
    GsonModule::class,
    HttpModule::class,
    DatabaseModule::class,
    AndroidInjectionModule::class,
    ActivityAppInjector::class
])
@ApplicationScope
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun app(): Application
    fun gson(): Gson
    fun database(): AppDatabase
    fun resources(): Resources

    fun inject(application: App)

    val launchComponentFactory: LaunchComponent.Factory

    /**
     * @SelfDocumented
     */
    @Component.Factory
    interface Factory {

        /**
         * @SelfDocumented
         */
        fun create(appModule: AppModule): AppComponent
    }
}