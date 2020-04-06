package com.vladislav.shumilov.launch_data.model.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.rocket_data.model.local.RocketImpl
import com.vladislav.shumilov.launch_domain.model.local.*
import com.vladislav.shumilov.mission_data.model.local.MissionImpl
import com.vladislav.shumilov.ship_data.model.local.ShipImpl

internal const val LAUNCH = "launch"

@Entity(
    tableName = LAUNCH,
    foreignKeys = [
        ForeignKey(
            entity = RocketImpl::class,
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
data class LaunchImpl(
    @PrimaryKey
    override var id: String,
    override var flight_number: Int?,
    override var upcoming: Boolean,
    override var launch_year: Int?,
    override var launch_date_unix: Int?,
    override var launch_date_utc: String?,
    override var is_tentative: Boolean,
    override var tentative_max_precision: String?,
    override var tbd: Boolean,
    override var launch_window: Int?,
    override var rocket_id: String?,
    override var launch_site_id: String?,
    override var launch_success: Boolean,
    override var details: String?,
    override var static_fire_date_utc: String?,
    override var static_fire_date_unix: Int?
) : Launch<MissionImpl, RocketImpl, ShipImpl, LaunchSiteImpl, LaunchFailureDetailsImpl, LinksImpl> {

    @Ignore
    override var missions: List<MissionImpl>? = null

    @Ignore
    override var rocket: RocketImpl? = null

    @Ignore
    override var ships: List<ShipImpl>? = null

    @Ignore
    override var launch_site: LaunchSiteImpl? = null

    @Ignore
    override var launch_failure_details: LaunchFailureDetailsImpl? = null

    @Ignore
    override var links: LinksImpl? = null
}