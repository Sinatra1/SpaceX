package com.vladislav.shumilov.launch_domain.model.remote

import com.example.rocket_domain.model.remote.RocketResponse

typealias LaunchResponseAlias = LaunchResponse<*, *, *, *>

interface LaunchResponse<R : RocketResponse<*, *, *>, LS : LaunchSiteResponse, LF : LaunchFailureDetailsResponse, L : LinksResponse> {

    val flight_number: Int?
    val mission_name: String?
    val mission_id: List<String>?
    val upcoming: Boolean
    val launch_year: Int?
    val launch_date_unix: Int?
    val launch_date_utc: String?
    val is_tentative: Boolean
    val tentative_max_precision: String?
    val tbd: Boolean
    val launch_window: Int?
    val rocket: R?
    val ships: List<String>?
    val launch_site: LS?
    val launch_success: Boolean
    val launch_failure_details: LF?
    val links: L?
    val details: String?
    val static_fire_date_utc: String?
    val static_fire_date_unix: Int?
}