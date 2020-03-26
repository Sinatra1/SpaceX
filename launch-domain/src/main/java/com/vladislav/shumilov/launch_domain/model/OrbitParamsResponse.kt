package com.vladislav.shumilov.launch_domain.model

interface OrbitParamsResponse {
    val reference_system: String?
    val regime: String?
    val longitude: Float?
    val semi_major_axis_km: Float?
    val eccentricity: Float?
    val periapsis_km: Float?
    val apoapsis_km: Float?
    val inclination_deg: Float?
    val period_min: Float?
    val lifespan_years: Float?
    val epoch: String?
    val mean_motion: Float?
    val raan: Float?
    val arg_of_pericenter: String?
    val mean_anomaly: String?
}