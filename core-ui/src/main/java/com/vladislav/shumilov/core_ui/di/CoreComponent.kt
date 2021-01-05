package com.vladislav.shumilov.core_ui

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.google.gson.Gson
import com.vladislav.shumilov.core_data.ApplicationContext
import com.vladislav.shumilov.core_data.CoreScope
import com.vladislav.shumilov.core_ui.injection.modules.AppModule
import com.vladislav.shumilov.core_ui.injection.modules.GsonModule
import com.vladislav.shumilov.core_ui.injection.modules.HttpModule
import dagger.Component
import retrofit2.Retrofit

@Component(
    modules = [
        AppModule::class,
        GsonModule::class,
        HttpModule::class
    ]
)
@CoreScope
interface CoreComponent {

    @ApplicationContext
    fun getContext(): Context

    fun getApp(): Application
    fun getGson(): Gson
    fun getResources(): Resources
    fun getRetrofit(): Retrofit

    @Component.Builder
    interface Builder {

        fun setAppModule(appModule: AppModule): Builder

        fun build(): CoreComponent
    }
}