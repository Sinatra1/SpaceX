package com.example.rocket_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rocket_data.model.local.CoreImpl
import io.reactivex.Single

@Dao
interface CoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(cores: List<CoreImpl>)

    @Query("select * from ${CoreImpl.TABLE_NAME}")
    fun getList(): Single<List<CoreImpl>>
}