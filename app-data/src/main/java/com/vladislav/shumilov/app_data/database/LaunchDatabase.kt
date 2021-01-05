package com.vladislav.shumilov.app_data.database

import com.vladislav.shumilov.launch_data.database.*

interface LaunchDatabase {
    fun getLaunchDao(): LaunchDao

    fun getLaunchSiteDao(): LaunchSiteDao

    fun getLaunchFailureDetailsDao(): LaunchFailureDetailsDao

    fun getLinksDao(): LinksDao

    fun getLaunchToMissionDao(): LaunchToMissionDao

    fun getLaunchToShipDao(): LaunchToShipDao
}