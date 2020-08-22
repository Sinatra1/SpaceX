package com.vladislav.shumilov.launch_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.launch_data.api.LAUNCHES_SORT
import com.vladislav.shumilov.launch_data.model.local.LaunchForDetailImpl
import com.vladislav.shumilov.launch_data.model.local.LaunchImpl
import com.vladislav.shumilov.launch_data.model.local.LaunchForListImpl
import io.reactivex.Single

@Dao
interface LaunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(launches: List<LaunchImpl>)

    @Query("SELECT * FROM ${LaunchImpl.TABLE_NAME} LIMIT :limit")
    fun getList(limit: Int = Int.MAX_VALUE): Single<List<LaunchImpl>>

    @Query("SELECT * FROM ${LaunchImpl.TABLE_NAME} ORDER BY :sort DESC LIMIT :limit OFFSET :offset")
    fun getLaunchesForList(
        offset: Int,
        limit: Int = Int.MAX_VALUE,
        sort: String = LAUNCHES_SORT
    ): Single<List<LaunchForListImpl>>

    @Query("SELECT * FROM ${LaunchImpl.TABLE_NAME} WHERE id = :launchId")
    fun getLaunchForDetail(launchId: String): Single<LaunchForDetailImpl>
}