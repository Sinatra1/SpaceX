package com.vladislav.shumilov.launch_data.model.local

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.rocket_data.model.local.RocketImpl
import com.vladislav.shumilov.core_data.util.databaseDateToHumanDate
import com.vladislav.shumilov.launch_domain.model.local.LaunchForDetail
import com.vladislav.shumilov.mission_data.model.local.MissionImpl

data class LaunchForDetailImpl(

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
    override val missions: List<MissionImpl>?,

    @Relation(
        parentColumn = LaunchImpl.Columns.ROCKET_ID,
        entity = RocketImpl::class,
        entityColumn = RocketImpl.Columns.ID
    )
    override val rocket: RocketImpl?,

    @Relation(
        parentColumn = LaunchImpl.Columns.LAUNCH_SITE_ID,
        entity = LaunchSiteImpl::class,
        entityColumn = LaunchSiteImpl.Columns.ID
    )
    override val launchSite: LaunchSiteImpl?,

    @Relation(
        parentColumn = LaunchImpl.Columns.ID,
        entity = LaunchFailureDetailsImpl::class,
        entityColumn = LaunchFailureDetailsImpl.Columns.LAUNCH_ID
    )
    override val launchFailureDetails: LaunchFailureDetailsImpl?,

    @Relation(
        parentColumn = LaunchImpl.Columns.ID,
        entity = LinksImpl::class,
        entityColumn = LinksImpl.Columns.LAUNCH_ID
    )
    override val links: LinksImpl?
) : LaunchForDetail {

    override val missionName: String
        get() = if (!missions.isNullOrEmpty()) {
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