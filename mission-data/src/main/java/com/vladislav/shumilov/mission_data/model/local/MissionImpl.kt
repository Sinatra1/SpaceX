package com.vladislav.shumilov.mission_data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladislav.shumilov.mission_data.model.local.MissionImpl.Companion.TABLE_NAME
import com.vladislav.shumilov.mission_domain.model.local.Mission

@Entity(tableName = TABLE_NAME)
data class MissionImpl(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override var id: String,

    @ColumnInfo(name = Columns.NAME)
    override var name: String?

) : Mission {
    @ColumnInfo(name = Columns.WIKIPEDIA)
    override var wikipedia: String? = null

    @ColumnInfo(name = Columns.WEBSITE)
    override var website: String? = null

    @ColumnInfo(name = Columns.TWITTER)
    override var twitter: String? = null

    @ColumnInfo(name = Columns.DESCRIPTION)
    override var description: String? = null

    companion object {
        const val TABLE_NAME = "mission"
    }

    class Columns {
        companion object {
            const val ID = "id"
            const val NAME = "name"
            const val WIKIPEDIA = "wikipedia"
            const val WEBSITE = "website"
            const val TWITTER = "twitter"
            const val DESCRIPTION = "description"
        }
    }
}