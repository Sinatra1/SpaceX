package com.vladislav.shumilov.rocket_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.rocket_data.model.local.PayloadImpl
import io.reactivex.Single

@Dao
interface PayloadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(payloads: List<PayloadImpl>)

    @Query("select * from ${PayloadImpl.TABLE_NAME}")
    fun getList(): Single<List<PayloadImpl>>
}