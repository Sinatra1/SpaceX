package com.example.rocket_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rocket_data.model.local.FirstStageImpl
import io.reactivex.Single

@Dao
interface FirstStageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(firstStages: List<FirstStageImpl>)

    @Query("select * from FIRST_STAGE")
    fun getList(): Single<List<FirstStageImpl>>
}