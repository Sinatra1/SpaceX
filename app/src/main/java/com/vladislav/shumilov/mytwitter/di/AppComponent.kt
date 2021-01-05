package com.vladislav.shumilov.mytwitter.di

import com.vladislav.shumilov.app_data.database.AppDatabase
import com.vladislav.shumilov.core_data.ApplicationScope
import com.vladislav.shumilov.core_ui.CoreComponent
import com.vladislav.shumilov.launch_ui.di.LaunchComponentHolder
import com.vladislav.shumilov.mytwitter.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    dependencies = [CoreComponent::class],
    modules = [
        AndroidSupportInjectionModule::class,
        DatabaseModule::class,
        ActivityBindersModule::class
    ]
)
@ApplicationScope
interface AppComponent : AndroidInjector<App>, LaunchComponentHolder {

    fun database(): AppDatabase

    @Component.Builder
    interface Builder {

        fun setCoreComponent(component: CoreComponent): Builder

        fun build(): AppComponent
    }
}