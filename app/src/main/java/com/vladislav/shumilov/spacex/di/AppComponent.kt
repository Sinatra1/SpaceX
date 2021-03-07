package com.vladislav.shumilov.spacex.di

import com.vladislav.shumilov.app_data.database.AppDatabase
import com.vladislav.shumilov.core_data.ApplicationScope
import com.vladislav.shumilov.core_ui.CoreComponent
import com.vladislav.shumilov.design_ui.di.DesignComponent
import com.vladislav.shumilov.launch_ui.di.LaunchComponentHolder
import com.vladislav.shumilov.spacex.App
import com.vladislav.shumilov.rocket_ui.di.RocketComponentHolder
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    dependencies = [CoreComponent::class, DesignComponent::class],
    modules = [
        AndroidSupportInjectionModule::class,
        DatabaseModule::class,
        ActivityBindersModule::class
    ]
)
@ApplicationScope
interface AppComponent : AndroidInjector<App>, LaunchComponentHolder, RocketComponentHolder {

    fun database(): AppDatabase

    @Component.Factory
    interface Factory {

        fun create(coreComponent: CoreComponent, designComponent: DesignComponent): AppComponent
    }
}