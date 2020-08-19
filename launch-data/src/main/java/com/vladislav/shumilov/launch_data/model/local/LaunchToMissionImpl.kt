package com.vladislav.shumilov.launch_data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.vladislav.shumilov.launch_data.model.local.LaunchToMissionImpl.Companion.TABLE_NAME
import com.vladislav.shumilov.launch_domain.model.local.LaunchToMission
import com.vladislav.shumilov.mission_data.model.local.MissionImpl

@Entity(
    tableName = TABLE_NAME,
    primaryKeys = [LaunchToMissionImpl.Columns.LAUNCH_ID, LaunchToMissionImpl.Columns.MISSION_ID],
    foreignKeys = [
        ForeignKey(
            entity = LaunchImpl::class,
            parentColumns = [LaunchImpl.Columns.ID],
            childColumns = [LaunchToMissionImpl.Columns.LAUNCH_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MissionImpl::class,
            parentColumns = [MissionImpl.Columns.ID],
            childColumns = [LaunchToMissionImpl.Columns.MISSION_ID],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LaunchToMissionImpl(
    @ColumnInfo(name = Columns.LAUNCH_ID)
    override val launchId: String,

    @ColumnInfo(name = Columns.MISSION_ID)
    override val missionId: String
) : LaunchToMission {

    companion object {
        const val TABLE_NAME = "launch_to_mission"
    }

    class Columns {
        companion object {
            const val LAUNCH_ID = "launchId"
            const val MISSION_ID = "mission_id"
        }
    }
}