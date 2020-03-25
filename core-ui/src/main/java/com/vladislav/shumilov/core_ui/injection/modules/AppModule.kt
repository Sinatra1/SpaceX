package com.vladislav.shumilov.core_ui.injection.modules

import android.app.Application
import android.content.Context
import com.vladislav.shumilov.core_data.ApplicationContext
import com.vladislav.shumilov.core_data.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
@ApplicationScope
class AppModule(private val app : Application) {

    private val appContext : Context

    init {
        appContext = app
    }

    @Provides
    @ApplicationContext
    fun provideContext(): Context = appContext

    @Provides
    @ApplicationScope
    fun provideApp() : Application = app

}