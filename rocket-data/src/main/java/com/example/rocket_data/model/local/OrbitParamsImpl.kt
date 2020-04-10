package com.example.rocket_data.model.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.rocket_domain.model.local.OrbitParams

internal const val ORBIT_PARAMS = "orbit_params"

@Entity(
    tableName = ORBIT_PARAMS,
    foreignKeys = [
        ForeignKey(
            entity = PayloadImpl::class,
            parentColumns = ["id"],
            childColumns = ["payload_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class OrbitParamsImpl(
    @PrimaryKey
    override var id: String,
    override var payload_id: String,
    override var reference_system: String?,
    override var regime: String?,
    override var longitude: Float?,
    override var semi_major_axis_km: Float?,
    override var eccentricity: Float?,
    override var periapsis_km: Float?,
    override var apoapsis_km: Float?,
    override var inclination_deg: Float?,
    override var period_min: Float?,
    override var lifespan_years: Float?,
    override var epoch: String?,
    override var mean_motion: Float?,
    override var raan: Float?,
    override var arg_of_pericenter: String?,
    override var mean_anomaly: String?
) : OrbitParams