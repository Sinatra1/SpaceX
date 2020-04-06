package com.vladislav.shumilov.launch_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.launch_data.model.local.LaunchToShipImpl
import io.reactivex.Single

@Dao
interface LaunchToShipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(launchToShips: List<LaunchToShipImpl>)

    @Query("select * from LAUNCH_TO_SHIP")
    fun getList(): Single<List<LaunchToShipImpl>>
}