package com.vladislav.shumilov.app_data.database

import com.vladislav.shumilov.mission_data.database.MissionDao

interface MissionDatabase {
    fun getMissionDao(): MissionDao
}
