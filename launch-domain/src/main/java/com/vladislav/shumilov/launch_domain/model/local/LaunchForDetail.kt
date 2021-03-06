package com.vladislav.shumilov.launch_domain.model.local

import com.vladislav.shumilov.rocket_domain.model.local.RocketForDetail
import com.vladislav.shumilov.mission_domain.model.local.Mission

interface LaunchForDetail {
    val launch: Launch
    val missions: List<Mission>?
    val rocketForDetail: RocketForDetail?
    val launchSite: LaunchSite?
    val launchFailureDetails: LaunchFailureDetails?
    val links: Links?

    var missionName: String?
    var flightNumberStr: String?
    var humanDateTime: String?
}