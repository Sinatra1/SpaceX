package com.vladislav.shumilov.launch_data.model.local

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.vladislav.shumilov.core_data.util.databaseDateToHumanDate
import com.vladislav.shumilov.launch_domain.model.local.LaunchWithMissions
import com.vladislav.shumilov.mission_data.model.local.MissionImpl

data class LaunchWithMissionsImpl(
    @Embedded
    override val launch: LaunchImpl,
    @Relation(
        parentColumn = LaunchImpl.Columns.ID,
        entity = MissionImpl::class,
        entityColumn = MissionImpl.Columns.ID,
        associateBy = Junction(
            value = LaunchToMissionImpl::class,
            parentColumn = LaunchToMissionImpl.Columns.LAUNCH_ID,
            entityColumn = LaunchToMissionImpl.Columns.MISSION_ID
        )

    )
    override val missions: List<MissionImpl>
) : LaunchWithMissions {

    override val missionName: String
        get() = if (missions.isNotEmpty()) {
            var missionNames = ""
            missions.forEachIndexed { index, mission ->
                if (index > 0) {
                    missionNames += " "
                }

                missionNames += mission.name
            }
            missionNames
        } else ""

    override val flightNumberStr: String
        get() = "#${launch.flightNumber}"

    override val humanDate: String
        get() = databaseDateToHumanDate(launch.launchDateUtc)
}