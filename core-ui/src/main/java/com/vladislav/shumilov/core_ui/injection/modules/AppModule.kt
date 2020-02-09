package com.vladislav.shumilov.core_ui.injection.modules

import android.app.Application
import android.content.Context
import com.vladislav.shumilov.core_ui.injection.ApplicationContext
import com.vladislav.shumilov.core_ui.injection.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
@ApplicationScope
class AppModule(val app : Application) {

    val appContext : Context

    init {
        appContext = app
    }

    @Provides
    @ApplicationContext
    fun provideContext() = appContext

    @Provides
    @ApplicationScope
    fun provideApp() : Application = app

}