package com.example.rocket_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rocket_data.model.local.PayloadImpl
import io.reactivex.Single

@Dao
interface PayloadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(payloads: List<PayloadImpl>)

    @Query("select * from PAYLOAD")
    fun getList(): Single<List<PayloadImpl>>
}