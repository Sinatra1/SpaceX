package com.vladislav.shumilov.rocket_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.rocket_data.model.local.CoreImpl

@Dao
interface CoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(cores: List<CoreImpl>)

    @Query("select * from ${CoreImpl.TABLE_NAME}")
    suspend fun getList(): List<CoreImpl>
}