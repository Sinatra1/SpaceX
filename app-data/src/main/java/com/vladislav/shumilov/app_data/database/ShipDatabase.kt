package com.vladislav.shumilov.app_data.database

import com.vladislav.shumilov.ship_data.database.ShipDao

interface ShipDatabase {
    fun getShipDao(): ShipDao
}