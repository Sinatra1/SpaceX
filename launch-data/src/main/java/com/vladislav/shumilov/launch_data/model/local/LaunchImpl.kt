package com.vladislav.shumilov.launch_data.model.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.rocket_data.model.local.RocketImpl
import com.vladislav.shumilov.launch_domain.model.local.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = MissionImpl::class,
            parentColumns = ["id"],
            childColumns = ["mission_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = com.example.rocket_data.model.local.RocketImpl::class,
            parentColumns = ["id"],
            childColumns = ["rocket_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = LaunchSiteImpl::class,
            parentColumns = ["id"],
            childColumns = ["launch_site_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class LaunchImpl(
    @PrimaryKey
    override var id: String,
    override var flight_number: Int?,
    /*override var mission_id: List<String>?,
    override var mission: MissionImpl?,*/
    override var upcoming: Boolean,
    override var launch_year: Int?,
    override var launch_date_unix: Int?,
    override var launch_date_utc: String?,
    override var is_tentative: Boolean,
    override var tentative_max_precision: String?,
    override var tbd: Boolean,
    override var launch_window: Int?,
    override var rocket_id: String?,
    override var rocket: RocketImpl?,
    /*override var ships: List<String>?,*/
    override var launch_site_id: String?,
    override var launch_site: LaunchSiteImpl?,
    override var launch_success: Boolean,
    override var launch_failure_details: LaunchFailureDetailsImpl?,
    override var links: LinksImpl?,
    override var details: String?,
    override var static_fire_date_utc: String?,
    override var static_fire_date_unix: Int?
) : Launch<MissionImpl, RocketImpl, LaunchSiteImpl, LaunchFailureDetailsImpl, LinksImpl>