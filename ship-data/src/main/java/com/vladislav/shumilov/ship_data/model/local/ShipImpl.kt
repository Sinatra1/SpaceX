package com.vladislav.shumilov.ship_data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladislav.shumilov.ship_domain.model.local.Ship

internal const val SHIP = "ship"

@Entity(tableName = SHIP)
data class ShipImpl(
    @PrimaryKey
    override var id: String,
    override var name: String?
) : Ship {
    override var type: String? = null
}