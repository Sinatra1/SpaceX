package com.vladislav.shumilov.rocket_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.rocket_data.model.local.SecondStageToPayloadImpl
import io.reactivex.Single

@Dao
interface SecondStageToPayloadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(secondStageToPayloads: List<SecondStageToPayloadImpl>)

    @Query("select * from ${SecondStageToPayloadImpl.TABLE_NAME}")
    fun getList(): Single<List<SecondStageToPayloadImpl>>
}