package com.example.rocket_data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rocket_domain.model.local.Core

internal const val CORE = "core"

@Entity(
    tableName = CORE
)
data class CoreImpl(
    @PrimaryKey
    override var id: String,
    override var serial: String?,
    override var flight: Int?,
    override var block: String?,
    override var gridfins: Boolean,
    override var legs: Boolean,
    override var reused: Boolean,
    override var land_success: Boolean?,
    override var landing_intent: Boolean,
    override var landing_type: String?,
    override var landing_vehicle: String?
) : Core