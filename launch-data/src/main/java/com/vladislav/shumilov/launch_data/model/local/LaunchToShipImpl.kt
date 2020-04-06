package com.vladislav.shumilov.launch_data.model.local

import androidx.room.Entity
import androidx.room.ForeignKey
import com.vladislav.shumilov.launch_domain.model.local.LaunchToShip
import com.vladislav.shumilov.ship_data.model.local.ShipImpl

internal const val LAUNCH_TO_SHIP = "launch_to_ship"

@Entity(
    tableName = LAUNCH_TO_SHIP,
    primaryKeys = ["launch_id", "ship_id"],
    foreignKeys = [
        ForeignKey(
            entity = LaunchImpl::class,
            parentColumns = ["id"],
            childColumns = ["launch_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ShipImpl::class,
            parentColumns = ["id"],
            childColumns = ["ship_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LaunchToShipImpl(
    override val launch_id: String,
    override val ship_id: String
) : LaunchToShip