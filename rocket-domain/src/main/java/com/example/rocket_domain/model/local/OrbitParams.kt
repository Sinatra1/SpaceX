package com.example.rocket_domain.model.local

interface OrbitParams {
    var reference_system: String?
    var regime: String?
    var longitude: Float?
    var semi_major_axis_km: Float?
    var eccentricity: Float?
    var periapsis_km: Float?
    var apoapsis_km: Float?
    var inclination_deg: Float?
    var period_min: Float?
    var lifespan_years: Float?
    var epoch: String?
    var mean_motion: Float?
    var raan: Float?
    var arg_of_pericenter: String?
    var mean_anomaly: String?
}