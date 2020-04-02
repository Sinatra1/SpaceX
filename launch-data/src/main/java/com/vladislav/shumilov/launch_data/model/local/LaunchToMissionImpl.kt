package com.vladislav.shumilov.launch_data.model.local

import androidx.room.Entity
import androidx.room.ForeignKey
import com.vladislav.shumilov.launch_domain.model.local.LaunchToMission
import com.vladislav.shumilov.mission_data.model.local.MissionImpl

internal const val LAUNCH_TO_MISSION = "launch_to_mission"

@Entity(
    tableName = LAUNCH_TO_MISSION,
    primaryKeys = ["launch_id", "mission_id"],
    foreignKeys = [
        ForeignKey(
            entity = LaunchImpl::class,
            parentColumns = ["id"],
            childColumns = ["launch_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MissionImpl::class,
            parentColumns = ["id"],
            childColumns = ["mission_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LaunchToMissionImpl(
    override val launch_id: String,
    override val mission_id: String
) : LaunchToMission