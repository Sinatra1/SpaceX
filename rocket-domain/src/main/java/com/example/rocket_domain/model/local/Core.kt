package com.example.rocket_domain.model.local

interface Core {
    var id: String
    var serial: String?
    var flight: Int?
    var block: String?
    var gridfins: Boolean
    var legs: Boolean
    var reused: Boolean
    var landSuccess: Boolean?
    var landingIntent: Boolean
    var landingType: String?
    var landingVehicle: String?
}