package com.vladislav.shumilov.launch_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.launch_data.api.LAUNCHES_SORT
import com.vladislav.shumilov.launch_data.model.local.LaunchForDetailImpl
import com.vladislav.shumilov.launch_data.model.local.LaunchImpl
import com.vladislav.shumilov.launch_data.model.local.LaunchForListImpl

@Dao
interface LaunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(launches: List<LaunchImpl>)

    @Query("SELECT * FROM ${LaunchImpl.TABLE_NAME} LIMIT :limit")
    suspend fun getList(limit: Int = Int.MAX_VALUE): List<LaunchImpl>

    @Query("SELECT * FROM ${LaunchImpl.TABLE_NAME} ORDER BY :sort DESC LIMIT :limit OFFSET :offset")
    suspend fun getLaunchesForList(
        offset: Int,
        limit: Int = Int.MAX_VALUE,
        sort: String = LAUNCHES_SORT
    ): List<LaunchForListImpl>

    @Query("SELECT * FROM ${LaunchImpl.TABLE_NAME} WHERE id = :launchId")
    suspend fun getLaunchForDetail(launchId: String): LaunchForDetailImpl
}