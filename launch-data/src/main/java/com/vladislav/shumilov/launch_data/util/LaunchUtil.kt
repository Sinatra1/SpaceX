package com.vladislav.shumilov.launch_data.util

import com.vladislav.shumilov.mission_domain.model.local.Mission

internal fun getMissionName(missions: List<Mission>?): String =
    if (!missions.isNullOrEmpty()) {
        var missionNames = ""
        val sortedMission = missions.sortedBy { it.name }
        sortedMission.forEachIndexed { index, mission ->
            if (index > 0) {
                missionNames += "/"
            }

            missionNames += mission.name
        }
        missionNames
    } else ""

internal fun getFlightNumberStr(flightNumber: Int) = "#${flightNumber}"