package com.example.rocket_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rocket_data.model.local.RocketImpl
import io.reactivex.Single

@Dao
interface RocketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(rockets: List<RocketImpl>)

    @Query("select * from ROCKET")
    fun getList(): Single<List<RocketImpl>>
}