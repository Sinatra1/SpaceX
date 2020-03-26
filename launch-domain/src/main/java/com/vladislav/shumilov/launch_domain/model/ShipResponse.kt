package com.vladislav.shumilov.launch_domain.model

interface ShipResponse<M : MissionResponse> {
    val ship_id: String?
    val ship_name: String?
    val ship_model: String?
    val ship_type: String?
    val roles: List<String>?
    val active: Boolean
    val imo: Int?
    val mmsi: Int?
    val abs: Int?
    val weight_lbs: Int?
    val weight_kg: Int?
    val year_built: Int?
    val home_port: String?
    val status: String?
    val speed_kn: Int?
    val course_deg: Int?
    val latitude: Float?
    val longitude: Float?
    val successful_landings: Int?
    val attempted_landings: Int?
    val missions: List<M>?
    val url: String?
    val image: String?

}