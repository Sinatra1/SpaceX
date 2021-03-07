package com.vladislav.shumilov.rocket_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.rocket_data.model.local.FairingsImpl
import io.reactivex.Single

@Dao
interface FairingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(firstStages: List<FairingsImpl>)

    @Query("select * from ${FairingsImpl.TABLE_NAME}")
    fun getList(): Single<List<FairingsImpl>>
}