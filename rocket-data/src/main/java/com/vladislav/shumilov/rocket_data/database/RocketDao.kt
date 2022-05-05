package com.vladislav.shumilov.rocket_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.rocket_data.model.local.RocketImpl

@Dao
interface RocketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(rockets: List<RocketImpl>)

    @Query("select * from ${RocketImpl.TABLE_NAME}")
    suspend fun getList(): List<RocketImpl>
}