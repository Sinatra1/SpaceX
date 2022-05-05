package com.vladislav.shumilov.launch_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.launch_data.model.local.LaunchFailureDetailsImpl

@Dao
interface LaunchFailureDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(launchFailureDetails: List<LaunchFailureDetailsImpl>)

    @Query("select * from ${LaunchFailureDetailsImpl.TABLE_NAME}")
    suspend fun getList(): List<LaunchFailureDetailsImpl>
}