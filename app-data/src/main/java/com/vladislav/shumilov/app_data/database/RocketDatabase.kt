package com.vladislav.shumilov.app_data.database

import com.vladislav.shumilov.rocket_data.database.RocketDao
import com.vladislav.shumilov.rocket_data.database.FirstStageDao
import com.vladislav.shumilov.rocket_data.database.SecondStageDao
import com.vladislav.shumilov.rocket_data.database.CoreDao
import com.vladislav.shumilov.rocket_data.database.FirstStageToCoreDao
import com.vladislav.shumilov.rocket_data.database.SecondStageToPayloadDao
import com.vladislav.shumilov.rocket_data.database.PayloadDao
import com.vladislav.shumilov.rocket_data.database.OrbitParamsDao
import com.vladislav.shumilov.rocket_data.database.FairingsDao

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
