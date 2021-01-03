package com.vladislav.shumilov.core_ui.injection.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vladislav.shumilov.core_data.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
@ApplicationScope
object GsonModule {

    @Provides
    @JvmStatic
    @ApplicationScope
    fun provideGson(): Gson =
        GsonBuilder()
            .setLenient().create()
}