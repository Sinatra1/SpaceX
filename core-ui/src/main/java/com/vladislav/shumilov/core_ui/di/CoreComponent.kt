package com.vladislav.shumilov.core_ui.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.google.gson.Gson
import com.vladislav.shumilov.core_data.ApplicationContext
import com.vladislav.shumilov.core_data.CoreScope
import com.vladislav.shumilov.core_ui.di.modules.HttpModule
import com.vladislav.shumilov.core_ui.injection.modules.AppModule
import com.vladislav.shumilov.core_ui.injection.modules.GsonModule
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

    @Component.Factory
    interface Factory {
        
        fun create(appModule: AppModule): CoreComponent
    }
}