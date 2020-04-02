package com.vladislav.shumilov.app_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rocket_data.database.FirstStageDao
import com.example.rocket_data.database.RocketDao
import com.example.rocket_data.database.SecondStageDao
import com.example.rocket_data.model.local.FairingsImpl
import com.example.rocket_data.model.local.FirstStageImpl
import com.example.rocket_data.model.local.RocketImpl
import com.example.rocket_data.model.local.SecondStageImpl
import com.vladislav.shumilov.core_data.database.Converters
import com.vladislav.shumilov.launch_data.database.*
import com.vladislav.shumilov.launch_data.model.local.*
import com.vladislav.shumilov.mission_data.database.MissionDao
import com.vladislav.shumilov.mission_data.model.local.MissionImpl

@Database(
    entities = [
        LaunchImpl::class,
        RocketImpl::class,
        MissionImpl::class,
        LaunchSiteImpl::class,
        LaunchFailureDetailsImpl::class,
        LinksImpl::class,
        LaunchToMissionImpl::class,
        FirstStageImpl::class,
        SecondStageImpl::class,
        FairingsImpl::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getLaunchDao(): LaunchDao

    abstract fun getRocketDao(): RocketDao

    abstract fun getMissionDao(): MissionDao

    abstract fun getLaunchSiteDao(): LaunchSiteDao

    abstract fun getLaunchFailureDetailsDao(): LaunchFailureDetailsDao

    abstract fun getLinksDao(): LinksDao

    abstract fun getLaunchToMissionDao(): LaunchToMissionDao

    abstract fun getFirstStageDao(): FirstStageDao

    abstract fun getSecondStageDao(): SecondStageDao


}