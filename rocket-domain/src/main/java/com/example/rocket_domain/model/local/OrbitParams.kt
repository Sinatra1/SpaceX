package com.example.rocket_domain.model.local

interface OrbitParams {
    var id: String
    var payloadId: String
    var referenceSystem: String?
    var regime: String?
    var longitude: Float?
    var semiMajorAxisKm: Float?
    var eccentricity: Float?
    var periapsisKm: Float?
    var apoapsisKm: Float?
    var inclinationDeg: Float?
    var periodMin: Float?
    var lifespanYears: Float?
    var epoch: String?
    var meanMotion: Float?
    var raan: Float?
    var argOfPericenter: String?
    var meanAnomaly: String?
}