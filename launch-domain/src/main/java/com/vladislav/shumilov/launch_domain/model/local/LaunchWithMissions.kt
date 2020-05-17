package com.vladislav.shumilov.launch_domain.model.local

import com.vladislav.shumilov.mission_domain.model.local.Mission

typealias LaunchWithMissionsAlias = LaunchWithMissions<*, *>

interface LaunchWithMissions<L: LaunchAlias, M: Mission> {
    val launch: L
    val missions: List<M>
}