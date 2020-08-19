package com.vladislav.shumilov.launch_domain.model.local

import com.example.rocket_domain.model.local.Rocket
import com.vladislav.shumilov.mission_domain.model.local.Mission
import com.vladislav.shumilov.ship_domain.model.local.Ship

interface Launch {
    var id: String
    var flightNumber: Int
    var missions: List<Mission>?
    var upcoming: Boolean
    var launchYear: Int?
    var launchDateUnix: Int?
    var launchDateUtc: String?
    var isTentative: Boolean
    var tentativeMaxPrecision: String?
    var tbd: Boolean
    var launchWindow: Int?
    var rocketId: String?
    var rocket: Rocket?
    var ships: List<Ship>?
    var launchSiteId: String?
    var launchSite: LaunchSite?
    var launchSuccess: Boolean
    var launchFailureDetails: LaunchFailureDetails?
    var links: Links?
    var details: String?
    var staticFireDateUtc: String?
    var staticFireDateUnix: Int?
}