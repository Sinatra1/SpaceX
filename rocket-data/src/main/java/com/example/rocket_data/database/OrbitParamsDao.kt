package com.example.rocket_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rocket_data.model.local.OrbitParamsImpl
import io.reactivex.Single

@Dao
interface OrbitParamsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(orbitParams: List<OrbitParamsImpl>)

    @Query("select * from ${OrbitParamsImpl.TABLE_NAME}")
    fun getList(): Single<List<OrbitParamsImpl>>
}