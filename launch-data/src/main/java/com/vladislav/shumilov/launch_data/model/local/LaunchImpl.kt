package com.vladislav.shumilov.launch_data.model.local

import androidx.room.*
import com.example.rocket_data.model.local.RocketImpl
import com.example.rocket_domain.model.local.Rocket
import com.vladislav.shumilov.launch_data.model.local.LaunchImpl.Companion.TABLE_NAME
import com.vladislav.shumilov.launch_domain.model.local.Launch
import com.vladislav.shumilov.launch_domain.model.local.LaunchFailureDetails
import com.vladislav.shumilov.launch_domain.model.local.LaunchSite
import com.vladislav.shumilov.launch_domain.model.local.Links
import com.vladislav.shumilov.mission_domain.model.local.Mission
import com.vladislav.shumilov.ship_domain.model.local.Ship

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = RocketImpl::class,
            parentColumns = [RocketImpl.Columns.ID],
            childColumns = [LaunchImpl.Columns.ROCKET_ID]
        ),
        ForeignKey(
            entity = LaunchSiteImpl::class,
            parentColumns = [LaunchSiteImpl.Columns.ID],
            childColumns = [LaunchImpl.Columns.LAUNCH_SITE_ID]
        )
    ],
    indices = [
        Index(value = [LaunchImpl.Columns.ROCKET_ID]),
        Index(value = [LaunchImpl.Columns.LAUNCH_SITE_ID]),
        Index(value = [LaunchImpl.Columns.FLIGHT_NUMBER], unique = true)
    ]
)
data class LaunchImpl(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override var id: String,

    @ColumnInfo(name = Columns.FLIGHT_NUMBER)
    override var flightNumber: Int,

    @ColumnInfo(name = Columns.UPCOMING)
    override var upcoming: Boolean,

    @ColumnInfo(name = Columns.LAUNCH_YEAR)
    override var launchYear: Int?,

    @ColumnInfo(name = Columns.LAUNCH_DATE_UNIX)
    override var launchDateUnix: Int?,

    @ColumnInfo(name = Columns.LAUNCH_DATE_UTC)
    override var launchDateUtc: String?,

    @ColumnInfo(name = Columns.IS_TENTATIVE)
    override var isTentative: Boolean,

    @ColumnInfo(name = Columns.TENTATIVE_MAX_PRECISION)
    override var tentativeMaxPrecision: String?,

    @ColumnInfo(name = Columns.TBD)
    override var tbd: Boolean,

    @ColumnInfo(name = Columns.LAUNCH_WINDOW)
    override var launchWindow: Int?,

    @ColumnInfo(name = Columns.ROCKET_ID)
    override var rocketId: String?,

    @ColumnInfo(name = Columns.LAUNCH_SITE_ID)
    override var launchSiteId: String?,

    @ColumnInfo(name = Columns.LAUNCH_SUCCESS)
    override var launchSuccess: Boolean,

    @ColumnInfo(name = Columns.DETAILS)
    override var details: String?,

    @ColumnInfo(name = Columns.STATIC_FIRE_DATE_UTC)
    override var staticFireDateUtc: String?,

    @ColumnInfo(name = Columns.STATIC_FIRE_DATE_UNIX)
    override var staticFireDateUnix: Int?
) : Launch {

    @Ignore
    override var missions: List<Mission>? = null

    @Ignore
    override var rocket: Rocket? = null

    @Ignore
    override var ships: List<Ship>? = null

    @Ignore
    override var launchSite: LaunchSite? = null

    @Ignore
    override var launchFailureDetails: LaunchFailureDetails? = null

    @Ignore
    override var links: Links? = null

    companion object {
        const val TABLE_NAME = "launch"
    }

    class Columns {
        companion object {
            const val ID = "id"
            const val FLIGHT_NUMBER = "flight_number"
            const val UPCOMING = "upcoming"
            const val LAUNCH_YEAR = "launch_year"
            const val LAUNCH_DATE_UNIX = "launch_date_unix"
            const val LAUNCH_DATE_UTC = "launch_date_utc"
            const val IS_TENTATIVE = "is_tentative"
            const val TENTATIVE_MAX_PRECISION = "tentative_max_precision"
            const val TBD = "tbd"
            const val LAUNCH_WINDOW = "launch_window"
            const val ROCKET_ID = "rocketId"
            const val LAUNCH_SITE_ID = "launch_site_id"
            const val LAUNCH_SUCCESS = "launch_success"
            const val DETAILS = "details"
            const val STATIC_FIRE_DATE_UTC = "static_fire_date_utc"
            const val STATIC_FIRE_DATE_UNIX = "static_fire_date_unix"
        }
    }
}