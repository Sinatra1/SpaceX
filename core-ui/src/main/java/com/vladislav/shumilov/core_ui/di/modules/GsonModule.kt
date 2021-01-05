package com.vladislav.shumilov.core_ui.injection.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vladislav.shumilov.core_data.CoreScope
import dagger.Module
import dagger.Provides

@Module
object GsonModule {

    @Provides
    @JvmStatic
    @CoreScope
    fun provideGson(): Gson =
        GsonBuilder()
            .setLenient().create()
}