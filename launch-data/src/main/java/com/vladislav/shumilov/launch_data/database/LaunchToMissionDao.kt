package com.vladislav.shumilov.launch_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.launch_data.model.local.LaunchToMissionImpl
import io.reactivex.Single

@Dao
interface LaunchToMissionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(launchToMissions: List<LaunchToMissionImpl>)

    @Query("select * from LAUNCH_TO_MISSION")
    fun getList(): Single<List<LaunchToMissionImpl>>
}