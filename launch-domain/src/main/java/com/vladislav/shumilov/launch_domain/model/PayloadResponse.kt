package com.vladislav.shumilov.launch_domain.model

interface PayloadResponse {
    var payload_id: String?
    var norad_id: List<String>?
    var reused: Boolean
    var customers: List<String>?
    var nationality: String?
    var manufacturer: String?
    var payload_type: String?
    var payload_mass_kg: Int?
    var payload_mass_lbs: Int?
    var orbit: String?
    var orbit_params: OrbitParamsResponse?
}