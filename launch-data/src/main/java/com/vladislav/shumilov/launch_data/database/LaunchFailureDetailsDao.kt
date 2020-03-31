package com.vladislav.shumilov.launch_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.launch_data.model.local.LaunchFailureDetailsImpl
import io.reactivex.Single

@Dao
interface LaunchFailureDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(launchFailureDetails: List<LaunchFailureDetailsImpl>)

    @Query("select * from LAUNCH_FAILURE_DETAILS")
    fun getList(): Single<List<LaunchFailureDetailsImpl>>
}