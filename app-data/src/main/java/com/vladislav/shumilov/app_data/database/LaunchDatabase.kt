package com.vladislav.shumilov.app_data.database

import com.vladislav.shumilov.launch_data.database.LaunchDao
import com.vladislav.shumilov.launch_data.database.LaunchSiteDao
import com.vladislav.shumilov.launch_data.database.LaunchFailureDetailsDao
import com.vladislav.shumilov.launch_data.database.LinksDao
import com.vladislav.shumilov.launch_data.database.LaunchToMissionDao
import com.vladislav.shumilov.launch_data.database.LaunchToShipDao

interface LaunchDatabase {
    fun getLaunchDao(): LaunchDao

    fun getLaunchSiteDao(): LaunchSiteDao

    fun getLaunchFailureDetailsDao(): LaunchFailureDetailsDao

    fun getLinksDao(): LinksDao

    fun getLaunchToMissionDao(): LaunchToMissionDao

    fun getLaunchToShipDao(): LaunchToShipDao
}
