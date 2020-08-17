package com.example.rocket_data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rocket_data.model.local.CoreImpl.Companion.TABLE_NAME
import com.example.rocket_domain.model.local.Core

@Entity(
    tableName = TABLE_NAME
)
data class CoreImpl(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override var id: String,
    @ColumnInfo(name = Columns.SERIAL)
    override var serial: String?,
    @ColumnInfo(name = Columns.FLIGHT)
    override var flight: Int?,
    @ColumnInfo(name = Columns.BLOCK)
    override var block: String?,
    @ColumnInfo(name = Columns.GRIDFINS)
    override var gridfins: Boolean,
    @ColumnInfo(name = Columns.LEGS)
    override var legs: Boolean,
    @ColumnInfo(name = Columns.REUSED)
    override var reused: Boolean,
    @ColumnInfo(name = Columns.LAND_SUCCESS)
    override var landSuccess: Boolean?,
    @ColumnInfo(name = Columns.LANDING_INTENT)
    override var landingIntent: Boolean,
    @ColumnInfo(name = Columns.LANDING_TYPE)
    override var landingType: String?,
    @ColumnInfo(name = Columns.LANDING_VEHICLE)
    override var landingVehicle: String?
) : Core {

    companion object {
        const val TABLE_NAME = "core"
    }

    class Columns {
        companion object {
            const val ID = "id"
            const val SERIAL = "serial"
            const val FLIGHT = "flight"
            const val BLOCK = "block"
            const val GRIDFINS = "gridfins"
            const val LEGS = "legs"
            const val REUSED = "reused"
            const val LAND_SUCCESS = "land_success"
            const val LANDING_INTENT = "landing_intent"
            const val LANDING_TYPE = "landing_type"
            const val LANDING_VEHICLE = "landing_vehicle"
        }
    }
}