package com.vladislav.shumilov.mytwitter.di

import android.content.Context
import androidx.room.Room
import com.example.rocket_data.database.*
import com.vladislav.shumilov.app_data.database.AppDatabase
import com.vladislav.shumilov.core_data.ApplicationContext
import com.vladislav.shumilov.core_data.ApplicationScope
import com.vladislav.shumilov.launch_data.database.*
import com.vladislav.shumilov.mission_data.database.MissionDao
import com.vladislav.shumilov.ship_data.database.ShipDao
import dagger.Module
import dagger.Provides

private const val DATABASE_NAME = "spacex_database"

@Module
class DatabaseModule {

    @Provides
    @ApplicationScope
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    @ApplicationScope
    fun provideLaunchDao(appDatabase: AppDatabase): LaunchDao =
        appDatabase.getLaunchDao()

    @Provides
    @ApplicationScope
    fun provideLaunchSiteDao(appDatabase: AppDatabase): LaunchSiteDao =
        appDatabase.getLaunchSiteDao()

    @Provides
    @ApplicationScope
    fun provideLaunchFailureDetailsDao(appDatabase: AppDatabase): LaunchFailureDetailsDao =
        appDatabase.getLaunchFailureDetailsDao()

    @Provides
    @ApplicationScope
    fun provideLinksDao(appDatabase: AppDatabase): LinksDao =
        appDatabase.getLinksDao()

    @Provides
    @ApplicationScope
    fun provideLaunchToMissionDao(appDatabase: AppDatabase): LaunchToMissionDao =
        appDatabase.getLaunchToMissionDao()

    @Provides
    @ApplicationScope
    fun provideLaunchToShipDao(appDatabase: AppDatabase): LaunchToShipDao =
        appDatabase.getLaunchToShipDao()

    @Provides
    @ApplicationScope
    fun provideRocketDao(@ApplicationScope appDatabase: AppDatabase): RocketDao =
        appDatabase.getRocketDao()

    @Provides
    @ApplicationScope
    fun provideFirstStageDao(@ApplicationScope appDatabase: AppDatabase): FirstStageDao =
        appDatabase.getFirstStageDao()

    @Provides
    @ApplicationScope
    fun provideSecondStageDao(@ApplicationScope appDatabase: AppDatabase): SecondStageDao =
        appDatabase.getSecondStageDao()

    @Provides
    @ApplicationScope
    fun provideCoreDao(@ApplicationScope appDatabase: AppDatabase): CoreDao =
        appDatabase.getCoreDao()

    @Provides
    @ApplicationScope
    fun provideFirstStageToCoreDao(@ApplicationScope appDatabase: AppDatabase): FirstStageToCoreDao =
        appDatabase.getFirstStageToCoreDao()

    @Provides
    @ApplicationScope
    fun provideSecondStageToPayloadDao(@ApplicationScope appDatabase: AppDatabase): SecondStageToPayloadDao =
        appDatabase.getSecondStageToPayloadDao()

    @Provides
    @ApplicationScope
    fun providePayloadDao(@ApplicationScope appDatabase: AppDatabase): PayloadDao =
        appDatabase.getPayloadDao()


    @Provides
    @ApplicationScope
    fun provideOrbitParamsDao(@ApplicationScope appDatabase: AppDatabase): OrbitParamsDao =
        appDatabase.getOrbitParamsDao()

    @Provides
    @ApplicationScope
    fun provideFairingsDao(@ApplicationScope appDatabase: AppDatabase): FairingsDao =
        appDatabase.getFairingsDao()

    @Provides
    @ApplicationScope
    fun provideMissionDao(@ApplicationScope appDatabase: AppDatabase): MissionDao =
        appDatabase.getMissionDao()

    @Provides
    @ApplicationScope
    fun provideShipDao(@ApplicationScope appDatabase: AppDatabase): ShipDao =
        appDatabase.getShipDao()
}