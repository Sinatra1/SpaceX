package com.vladislav.shumilov.launch_data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.shumilov.launch_data.model.local.LinksImpl
import io.reactivex.Single

@Dao
interface LinksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(links: List<LinksImpl>)

    @Query("select * from ${LinksImpl.TABLE_NAME}")
    fun getList(): Single<List<LinksImpl>>
}