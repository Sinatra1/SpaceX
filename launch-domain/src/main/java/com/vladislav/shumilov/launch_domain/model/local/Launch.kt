package com.vladislav.shumilov.launch_domain.model.local

import com.example.rocket_domain.model.local.Rocket
import com.vladislav.shumilov.mission_domain.model.local.Mission

typealias LaunchAlias = Launch<*, *, *, *, *>

interface Launch<M: Mission, R: Rocket<*, *, *>, LS: LaunchSite, LF: LaunchFailureDetails, L: Links> {
    var id: String
    var flight_number: Int?
    var missions: List<M>?
    var upcoming: Boolean
    var launch_year: Int?
    var launch_date_unix: Int?
    var launch_date_utc: String?
    var is_tentative: Boolean
    var tentative_max_precision: String?
    var tbd: Boolean
    var launch_window: Int?
    var rocket_id: String?
    var rocket: R?
    var ships: List<String>?
    var launch_site_id: String?
    var launch_site: LS?
    var launch_success: Boolean
    var launch_failure_details: LF?
    var links: L?
    var details: String?
    var static_fire_date_utc: String?
    var static_fire_date_unix: Int?
}