package com.vladislav.shumilov.launch_domain.model.local

import com.vladislav.shumilov.mission_domain.model.local.Mission

interface LaunchWithMissions {
    val launch: Launch
    val missions: List<Mission>
    val missionName: String?
    val flightNumberStr: String?
}