package com.vladislav.shumilov.launch_data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladislav.shumilov.launch_data.model.local.LaunchSiteImpl.Companion.TABLE_NAME
import com.vladislav.shumilov.launch_domain.model.local.LaunchSite


@Entity(tableName = TABLE_NAME)
data class LaunchSiteImpl(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override var id: String,

    @ColumnInfo(name = Columns.NAME)
    override var name: String?,

    @ColumnInfo(name = Columns.NAME_LONG)
    override var nameLong: String?
) : LaunchSite {

    companion object {
        const val TABLE_NAME = "launchSite"
    }

    class Columns {
        companion object {
            const val ID = "id"
            const val NAME = "name"
            const val NAME_LONG = "name_long"
        }
    }
}