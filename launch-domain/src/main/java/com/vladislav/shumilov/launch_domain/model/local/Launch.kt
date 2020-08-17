package com.vladislav.shumilov.launch_domain.model.local

import com.example.rocket_domain.model.local.Rocket
import com.vladislav.shumilov.mission_domain.model.local.Mission
import com.vladislav.shumilov.ship_domain.model.local.Ship

typealias LaunchAlias = Launch<*, *, *, *, *, *>

interface Launch<M: Mission, R: Rocket<*, *, *>, S: Ship, LS: LaunchSite, LF: LaunchFailureDetails, L: Links> {
    var id: String
    var flightNumber: Int
    var missions: List<M>?
    var upcoming: Boolean
    var launchYear: Int?
    var launchDateUnix: Int?
    var launchDateUtc: String?
    var isTentative: Boolean
    var tentativeMaxPrecision: String?
    var tbd: Boolean
    var launchWindow: Int?
    var rocketId: String?
    var rocket: R?
    var ships: List<S>?
    var launchSiteId: String?
    var launch_site: LS?
    var launchSuccess: Boolean
    var launch_failure_details: LF?
    var links: L?
    var details: String?
    var staticFireDateUtc: String?
    var staticFireDateUnix: Int?
}