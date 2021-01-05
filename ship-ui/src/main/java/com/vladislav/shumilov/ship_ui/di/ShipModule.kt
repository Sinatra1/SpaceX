package com.vladislav.shumilov.ship_ui.di

import com.vladislav.shumilov.app_data.database.AppDatabase
import com.vladislav.shumilov.core_data.ApplicationScope
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.ship_data.database.ShipDao
import dagger.Module
import dagger.Provides

@Module
object ShipModule {

    @Provides
    @JvmStatic
    @FragmentScope
    fun provideShipDao(@ApplicationScope appDatabase: AppDatabase): ShipDao =
        appDatabase.getShipDao()
}