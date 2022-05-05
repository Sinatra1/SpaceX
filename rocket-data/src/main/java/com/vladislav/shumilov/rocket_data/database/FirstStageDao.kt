package com.vladislav.shumilov.rocket_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.rocket_data.model.local.FirstStageImpl

@Dao
interface FirstStageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(firstStages: List<FirstStageImpl>)

    @Query("select * from ${FirstStageImpl.TABLE_NAME}")
    suspend fun getList(): List<FirstStageImpl>
}