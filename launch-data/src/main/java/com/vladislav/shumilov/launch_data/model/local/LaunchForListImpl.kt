package com.vladislav.shumilov.launch_data.model.local

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.vladislav.shumilov.core_data.util.unixTimeToHumanDateTime
import com.vladislav.shumilov.launch_data.util.getFlightNumberStr
import com.vladislav.shumilov.launch_data.util.getMissionName
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import com.vladislav.shumilov.mission_data.model.local.MissionImpl

data class LaunchForListImpl(
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
        parentColumn = LaunchImpl.Columns.ID,
        entity = LinksImpl::class,
        entityColumn = LinksImpl.Columns.LAUNCH_ID
    )
    override val links: LinksImpl?

) : LaunchForList {

    override var missionName: String? = getMissionName(missions)

    override var flightNumberStr: String? = getFlightNumberStr(launch.flightNumber)

    override var humanDateTime: String? = unixTimeToHumanDateTime(launch.launchDateUnix)
}