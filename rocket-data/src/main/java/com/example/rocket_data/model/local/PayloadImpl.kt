package com.example.rocket_data.model.local

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.rocket_domain.model.local.Payload

internal const val PAYLOAD = "payload"

@Entity(tableName = PAYLOAD)
data class PayloadImpl(
    @PrimaryKey
    override var id: String,
    override var norad_id: List<String>?,
    override var reused: Boolean,
    override var customers: List<String>?,
    override var nationality: String?,
    override var manufacturer: String?,
    override var type: String?,
    override var mass_kg: Float?,
    override var mass_lbs: Float?,
    override var orbit: String?
) : Payload<OrbitParamsImpl> {

    @Ignore
    override var orbit_params: OrbitParamsImpl? = null
}