package com.vladislav.shumilov.launch_data.model

import com.vladislav.shumilov.launch_domain.model.*


class LaunchResponseImpl : LaunchResponse {
    override var flight_number: Int? = null
    override var mission_name: String? = null
    override var mission_id: List<String>? = null
    override var upcoming: Boolean = false
    override var launch_year: Int? = null
    override var launch_date_unix: Int? = null
    override var launch_date_utc: String? = null
    override var is_tentative: Boolean = false
    override var tentative_max_precision: String? = null
    override var tbd: Boolean = false
    override var launch_window: Int? = null
    override var rocket: RocketResponse? = null
    override var ships: List<ShipResponse>? = null
    override var launch_site: LaunchSiteResponse? = null
    override var launch_success: Boolean = false
    override var launch_failure_details: LaunchFailureDetailsResponse? = null
    override var links: LinksResponse? = null
    override var details: String? = null
    override var static_fire_date_utc: String? = null
    override var static_fire_date_unix: Int? = null
}