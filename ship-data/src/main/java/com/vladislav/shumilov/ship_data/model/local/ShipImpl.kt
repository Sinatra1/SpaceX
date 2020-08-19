package com.vladislav.shumilov.ship_data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladislav.shumilov.ship_data.model.local.ShipImpl.Companion.TABLE_NAME
import com.vladislav.shumilov.ship_domain.model.local.Ship


@Entity(tableName = TABLE_NAME)
data class ShipImpl(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override var id: String,
    @ColumnInfo(name = Columns.NAME)
    override var name: String?
) : Ship {
    @ColumnInfo(name = Columns.TYPE)
    override var type: String? = null

    companion object {
        const val TABLE_NAME = "ship"
    }

    class Columns {
        companion object {
            const val ID = "id"
            const val NAME = "name"
            const val TYPE = "type"
        }
    }
}