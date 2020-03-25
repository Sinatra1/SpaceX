package com.vladislav.shumilov.launch_domain.model

interface ShipResponse {
    var ship_id: String?
    var ship_name: String?
    var ship_model: String?
    var ship_type: String?
    var roles: List<String>?
    var active: Boolean
    var imo: Int?
    var mmsi: Int?
    var abs: Int?
    var weight_lbs: Int?
    var weight_kg: Int?
    var year_built: Int?
    var home_port: String?
    var status: String?
    var speed_kn: Int?
    var course_deg: Int?
    var latitude: Float?
    var longitude: Float?
    var successful_landings: Int?
    var attempted_landings: Int?
    var missions: List<MissionResponse>?
    var url: String?
    var image: String?

}