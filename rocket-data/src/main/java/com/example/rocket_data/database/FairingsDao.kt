package com.example.rocket_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rocket_data.model.local.FairingsImpl
import io.reactivex.Single

@Dao
interface FairingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(firstStages: List<FairingsImpl>)

    @Query("select * from FAIRINGS")
    fun getList(): Single<List<FairingsImpl>>
}