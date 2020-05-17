package com.vladislav.shumilov.launch_data.model.local

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Junction
import androidx.room.Relation
import com.vladislav.shumilov.launch_domain.model.local.LaunchWithMissions
import com.vladislav.shumilov.mission_data.model.local.MissionImpl

data class LaunchWithMissionsImpl(
    @Embedded
    override val launch: LaunchImpl,
    @Relation(
        parentColumn = "id",
        entity = MissionImpl::class,
        entityColumn = "id",
        associateBy = Junction(
            value = LaunchToMissionImpl::class,
            parentColumn = "launch_id",
            entityColumn = "mission_id"
        )

    )
    override val missions: List<MissionImpl>
) : LaunchWithMissions<LaunchImpl, MissionImpl> {

    @Ignore
    var missionName: String? = null
        get() {
            if (field == null) {
                field = if (missions.isNotEmpty()) {
                    var missionNames = ""
                    missions.forEachIndexed { index, mission ->
                        if (index > 0) {
                            missionNames += " "
                        }

                        missionNames += mission.name
                    }
                    missionNames
                } else ""
            }
            return field
        }


    @Ignore
    var flightNumberStr: String? = null
        get() {
            if (field == null) {
                field = launch.flight_number.toString()
            }

            return field
        }
}