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
import com.vladislav.shumilov.launch_ui.di.LaunchAppComponent
import com.vladislav.shumilov.mytwitter.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        GsonModule::class,
        HttpModule::class,
        DatabaseModule::class,
        ActivityBindersModule::class
    ]
)
@ApplicationScope
interface AppComponent : AndroidInjector<App>, LaunchAppComponent {

    @ApplicationContext
    fun context(): Context

    fun app(): Application
    fun gson(): Gson
    fun database(): AppDatabase
    fun resources(): Resources

    @Component.Builder
    interface Builder {

        fun appModule(appModule: AppModule): Builder

        fun build(): AppComponent
    }
}