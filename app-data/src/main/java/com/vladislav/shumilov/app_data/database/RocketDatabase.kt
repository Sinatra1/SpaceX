package com.vladislav.shumilov.app_data.database

import com.vladislav.shumilov.rocket_data.database.*

interface RocketDatabase {

    fun getRocketDao(): RocketDao

    fun getFirstStageDao(): FirstStageDao

    fun getSecondStageDao(): SecondStageDao

    fun getCoreDao(): CoreDao

    fun getFirstStageToCoreDao(): FirstStageToCoreDao

    fun getSecondStageToPayloadDao(): SecondStageToPayloadDao

    fun getPayloadDao(): PayloadDao

    fun getOrbitParamsDao(): OrbitParamsDao

    fun getFairingsDao(): FairingsDao
}