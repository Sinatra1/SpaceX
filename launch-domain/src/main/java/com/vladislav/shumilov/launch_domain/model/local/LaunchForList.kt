package com.vladislav.shumilov.launch_domain.model.local

import com.vladislav.shumilov.mission_domain.model.local.Mission

interface LaunchForList {
    val launch: Launch
    val missions: List<Mission>?
    val links: Links?
    var missionName: String?
    var flightNumberStr: String?
    var humanDateTime: String?
}