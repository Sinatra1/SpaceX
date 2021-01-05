package com.vladislav.shumiov.mission_ui.di

import com.vladislav.shumilov.app_data.database.AppDatabase
import com.vladislav.shumilov.core_data.ApplicationScope
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.mission_data.database.MissionDao
import dagger.Module
import dagger.Provides

@Module
object MissionModule {

    @Provides
    @JvmStatic
    @FragmentScope
    fun provideMissionDao(@ApplicationScope appDatabase: AppDatabase): MissionDao =
        appDatabase.getMissionDao()
}