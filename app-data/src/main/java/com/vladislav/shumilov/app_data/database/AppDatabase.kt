package com.vladislav.shumilov.app_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rocket_data.database.FirstStageDao
import com.example.rocket_data.database.RocketDao
import com.example.rocket_data.database.SecondStageDao
import com.example.rocket_data.model.local.FairingsImpl
import com.example.rocket_data.model.local.FirstStageImpl
import com.example.rocket_data.model.local.RocketImpl
import com.example.rocket_data.model.local.SecondStageImpl
import com.vladislav.shumilov.launch_data.database.LaunchDao
import com.vladislav.shumilov.launch_data.database.LaunchFailureDetailsDao
import com.vladislav.shumilov.launch_data.database.LaunchSiteDao
import com.vladislav.shumilov.launch_data.database.LinksDao
import com.vladislav.shumilov.launch_data.model.local.LaunchFailureDetailsImpl
import com.vladislav.shumilov.launch_data.model.local.LaunchImpl
import com.vladislav.shumilov.launch_data.model.local.LaunchSiteImpl
import com.vladislav.shumilov.launch_data.model.local.LinksImpl

@Database(
    entities = [
        LaunchImpl::class,
        RocketImpl::class,
        LaunchSiteImpl::class,
        LaunchFailureDetailsImpl::class,
        LinksImpl::class,
        FirstStageImpl::class,
        SecondStageImpl::class,
        FairingsImpl::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getLaunchDao(): LaunchDao

    abstract fun getRocketDao(): RocketDao

    abstract fun getLaunchSiteDao(): LaunchSiteDao

    abstract fun getLaunchFailureDetailsDao(): LaunchFailureDetailsDao

    abstract fun getLinksDao(): LinksDao

    abstract fun getFirstStageDao(): FirstStageDao

    abstract fun getSecondStageDao(): SecondStageDao


}