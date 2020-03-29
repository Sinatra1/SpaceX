package com.example.rocket_data.model.remote

import com.example.rocket_domain.model.remote.OrbitParamsResponse

class OrbitParamsResponseImpl(
    override val reference_system: String?,
    override val regime: String?,
    override val longitude: Float?,
    override val semi_major_axis_km: Float?,
    override val eccentricity: Float?,
    override val periapsis_km: Float?,
    override val apoapsis_km: Float?,
    override val inclination_deg: Float?,
    override val period_min: Float?,
    override val lifespan_years: Float?,
    override val epoch: String?,
    override val mean_motion: Float?,
    override val raan: Float?,
    override val arg_of_pericenter: String?,
    override val mean_anomaly: String?
) : OrbitParamsResponse