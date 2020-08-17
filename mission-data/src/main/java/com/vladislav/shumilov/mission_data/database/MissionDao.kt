package com.vladislav.shumilov.mission_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.mission_data.model.local.MissionImpl
import io.reactivex.Single

@Dao
interface MissionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(missions: List<MissionImpl>)

    @Query("select * from ${MissionImpl.TABLE_NAME}")
    fun getList(): Single<List<MissionImpl>>
}