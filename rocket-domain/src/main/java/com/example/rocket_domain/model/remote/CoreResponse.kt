package com.example.rocket_domain.model.remote

interface CoreResponse {
    val core_serial: String
    val flight: Int?
    val block: String?
    val gridfins: Boolean
    val legs: Boolean
    val reused: Boolean
    val land_success: Boolean?
    val landing_intent: Boolean
    val landing_type: String?
    val landing_vehicle: String?
}