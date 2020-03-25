package com.vladislav.shumilov.mytwitter.components

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.vladislav.shumilov.core_data.ApplicationContext
import com.vladislav.shumilov.core_data.ApplicationScope
import com.vladislav.shumilov.core_data.database.LocalDatabase
import com.vladislav.shumilov.core_ui.injection.modules.AppModule
import com.vladislav.shumilov.core_ui.injection.modules.DataBaseModule
import com.vladislav.shumilov.core_ui.injection.modules.GsonModule
import com.vladislav.shumilov.core_ui.injection.modules.HttpModule
import com.vladislav.shumilov.launch_ui.di.LaunchAppComponent
import dagger.Component

@Component(modules = [AppModule::class, GsonModule::class, HttpModule::class, DataBaseModule::class])
@ApplicationScope
interface AppComponent: LaunchAppComponent {

    @ApplicationContext
    fun context(): Context

    fun app(): Application
    fun gson(): Gson
    fun database(): LocalDatabase
}