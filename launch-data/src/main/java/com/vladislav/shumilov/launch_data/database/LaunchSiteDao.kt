package com.vladislav.shumilov.launch_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.launch_data.model.local.LaunchSiteImpl
import io.reactivex.Single

@Dao
interface LaunchSiteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(launchSites: List<LaunchSiteImpl>)

    @Query("select * from LAUNCH_SITE")
    fun getList(): Single<List<LaunchSiteImpl>>
}