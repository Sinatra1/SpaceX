package com.vladislav.shumilov.launch_domain.model.local

import com.example.rocket_domain.model.local.Rocket
import com.vladislav.shumilov.mission_domain.model.local.Mission

interface LaunchForDetail {
    val launch: Launch
    val missions: List<Mission>?
    val rocket: Rocket?
    val launchSite: LaunchSite?
    val launchFailureDetails: LaunchFailureDetails?
    val links: Links?

    val missionName: String
    val flightNumberStr: String
    val humanDate: String
}