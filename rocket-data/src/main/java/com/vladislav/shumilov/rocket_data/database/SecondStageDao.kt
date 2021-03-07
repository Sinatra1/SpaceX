package com.vladislav.shumilov.rocket_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.rocket_data.model.local.SecondStageImpl
import io.reactivex.Single

@Dao
interface SecondStageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(secondStages: List<SecondStageImpl>)

    @Query("select * from ${SecondStageImpl.TABLE_NAME}")
    fun getList(): Single<List<SecondStageImpl>>
}