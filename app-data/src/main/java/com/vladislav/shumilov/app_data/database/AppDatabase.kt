package com.vladislav.shumilov.app_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vladislav.shumilov.rocket_data.model.local.*
import com.vladislav.shumilov.rocket_data.model.local.CoreImpl
import com.vladislav.shumilov.core_data.database.Converters
import com.vladislav.shumilov.launch_data.model.local.*
import com.vladislav.shumilov.mission_data.model.local.MissionImpl
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
abstract class AppDatabase : RoomDatabase(),
    LaunchDatabase, RocketDatabase,
    ShipDatabase,
    MissionDatabase