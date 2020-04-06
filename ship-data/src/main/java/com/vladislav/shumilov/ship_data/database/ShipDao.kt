package com.vladislav.shumilov.ship_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.ship_data.model.local.ShipImpl
import io.reactivex.Single

@Dao
interface ShipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(missions: List<ShipImpl>)

    @Query("select * from SHIP")
    fun getList(): Single<List<ShipImpl>>
}