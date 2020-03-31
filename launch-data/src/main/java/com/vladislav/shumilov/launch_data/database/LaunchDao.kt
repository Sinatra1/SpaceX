package com.vladislav.shumilov.launch_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.launch_data.model.local.LaunchImpl
import io.reactivex.Single

@Dao
interface LaunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(launches: List<LaunchImpl>)

    @Query("select * from LAUNCH")
    fun getList(): Single<List<LaunchImpl>>
}