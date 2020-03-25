package com.vladislav.shumilov.launch_domain.model

interface LaunchResponse {

    var flight_number: Int?
    var mission_name: String?
    var mission_id: List<String>?
    var upcoming: Boolean
    var launch_year: Int?
    var launch_date_unix: Int?
    var launch_date_utc: String?
    var is_tentative: Boolean
    var tentative_max_precision: String?
    var tbd: Boolean
    var launch_window: Int?
    var rocket: RocketResponse?
    var ships: List<ShipResponse>?
    var launch_site: LaunchSiteResponse?
    var launch_success: Boolean
    var launch_failure_details: LaunchFailureDetailsResponse?
    var links: LinksResponse?
    var details: String?
    var static_fire_date_utc: String?
    var static_fire_date_unix: Int?
}