package com.vladislav.shumilov.app_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rocket_data.database.*
import com.example.rocket_data.model.local.*
import com.vladislav.shumilov.core_data.database.Converters
import com.example.rocket_data.model.local.CoreImpl
import com.vladislav.shumilov.launch_data.database.*
import com.vladislav.shumilov.launch_data.model.local.*
import com.vladislav.shumilov.mission_data.database.MissionDao
import com.vladislav.shumilov.mission_data.model.local.MissionImpl
import com.vladislav.shumilov.ship_data.database.ShipDao
import com.vladislav.shumilov.ship_data.model.local.ShipImpl

@Database(
    entities = [
        LaunchImpl::class,
        RocketImpl::class,
        ShipImpl::class,
        MissionImpl::class,
        LaunchSiteImpl::class,
        LaunchFailureDetailsImpl::class,
        LinksImpl::class,
        LaunchToMissionImpl::class,
        LaunchToShipImpl::class,
        FirstStageImpl::class,
        SecondStageImpl::class,
        FairingsImpl::class,
        CoreImpl::class,
        FirstStageToCoreImpl::class,
        SecondStageToPayloadImpl::class,
        PayloadImpl::class,
        OrbitParamsImpl::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getLaunchDao(): LaunchDao

    abstract fun getRocketDao(): RocketDao

    abstract fun getShipDao(): ShipDao

    abstract fun getMissionDao(): MissionDao

    abstract fun getLaunchSiteDao(): LaunchSiteDao

    abstract fun getLaunchFailureDetailsDao(): LaunchFailureDetailsDao

    abstract fun getLinksDao(): LinksDao

    abstract fun getLaunchToMissionDao(): LaunchToMissionDao

    abstract fun getLaunchToShipDao(): LaunchToShipDao

    abstract fun getFirstStageDao(): FirstStageDao

    abstract fun getSecondStageDao(): SecondStageDao

    abstract fun getCoreDao(): CoreDao

    abstract fun getFirstStageToCoreDao(): FirstStageToCoreDao

    abstract fun getSecondStageToPayloadDao(): SecondStageToPayloadDao

    abstract fun getPayloadDao(): PayloadDao

    abstract fun getOrbitParamsDao(): OrbitParamsDao
}