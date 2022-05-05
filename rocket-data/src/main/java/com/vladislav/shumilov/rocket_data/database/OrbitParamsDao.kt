package com.vladislav.shumilov.rocket_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.rocket_data.model.local.OrbitParamsImpl

@Dao
interface OrbitParamsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(orbitParams: List<OrbitParamsImpl>)

    @Query("select * from ${OrbitParamsImpl.TABLE_NAME}")
    suspend fun getList(): List<OrbitParamsImpl>
}