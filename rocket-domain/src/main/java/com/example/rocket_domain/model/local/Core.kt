package com.example.rocket_domain.model.local

interface Core {
    var serial: String?
    var flight: Int?
    var block: String?
    var gridfins: Boolean
    var legs: Boolean
    var reused: Boolean
    var land_success: Boolean?
    var landing_intent: Boolean
    var landing_type: String?
    var landing_vehicle: String?
}