package com.example.rocket_domain.model.local

interface Payload<O : OrbitParams> {
    var id: String
    var norad_id: List<String>?
    var reused: Boolean
    var customers: List<String>?
    var nationality: String?
    var manufacturer: String?
    var type: String?
    var mass_kg: Float?
    var mass_lbs: Float?
    var orbit: String?
    var orbit_params: O?
}