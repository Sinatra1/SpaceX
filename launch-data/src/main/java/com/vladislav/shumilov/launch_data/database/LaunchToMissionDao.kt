package com.vladislav.shumilov.launch_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.launch_data.model.local.LaunchToMissionImpl

@Dao
interface LaunchToMissionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(launchToMissions: List<LaunchToMissionImpl>)

    @Query("select * from ${LaunchToMissionImpl.TABLE_NAME}")
    suspend fun getList(): List<LaunchToMissionImpl>
}