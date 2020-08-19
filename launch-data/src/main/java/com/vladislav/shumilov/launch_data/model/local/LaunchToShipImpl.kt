package com.vladislav.shumilov.launch_data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.vladislav.shumilov.launch_data.model.local.LaunchToShipImpl.Companion.TABLE_NAME
import com.vladislav.shumilov.launch_domain.model.local.LaunchToShip
import com.vladislav.shumilov.ship_data.model.local.ShipImpl

@Entity(
    tableName = TABLE_NAME,
    primaryKeys = [LaunchToShipImpl.Columns.LAUNCH_ID, LaunchToShipImpl.Columns.SHIP_ID],
    foreignKeys = [
        ForeignKey(
            entity = LaunchImpl::class,
            parentColumns = [LaunchImpl.Columns.ID],
            childColumns = [LaunchToShipImpl.Columns.LAUNCH_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ShipImpl::class,
            parentColumns = [ShipImpl.Columns.ID],
            childColumns = [LaunchToShipImpl.Columns.SHIP_ID],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LaunchToShipImpl(
    @ColumnInfo(name = Columns.LAUNCH_ID)
    override val launchId: String,

    @ColumnInfo(name = Columns.SHIP_ID)
    override val shipId: String
) : LaunchToShip {

    companion object {
        const val TABLE_NAME = "launch_to_ship"
    }

    class Columns {
        companion object {
            const val LAUNCH_ID = "launchId"
            const val SHIP_ID = "ship_id"
        }
    }
}