package com.vladislav.shumilov.launch_domain.model

interface PayloadResponse<O : OrbitParamsResponse> {
    val payload_id: String?
    val norad_id: List<String>?
    val reused: Boolean
    val customers: List<String>?
    val nationality: String?
    val manufacturer: String?
    val payload_type: String?
    val payload_mass_kg: Float?
    val payload_mass_lbs: Float?
    val orbit: String?
    val orbit_params: O?
}