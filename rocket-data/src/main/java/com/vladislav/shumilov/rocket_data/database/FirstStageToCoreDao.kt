package com.vladislav.shumilov.rocket_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.rocket_data.model.local.FirstStageToCoreImpl
import io.reactivex.Single

@Dao
interface FirstStageToCoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(firstStageToCores: List<FirstStageToCoreImpl>)

    @Query("select * from ${FirstStageToCoreImpl.TABLE_NAME}")
    fun getList(): Single<List<FirstStageToCoreImpl>>
}