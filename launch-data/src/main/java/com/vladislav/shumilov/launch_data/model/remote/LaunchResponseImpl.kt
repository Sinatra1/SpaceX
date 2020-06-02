package com.vladislav.shumilov.launch_data.model.remote

import com.example.rocket_data.model.remote.RocketResponseImpl
import com.vladislav.shumilov.launch_domain.model.remote.LaunchResponse


class LaunchResponseImpl(
    override val flight_number: Int,
    override val mission_name: String?,
    override val mission_id: List<String>?,
    override val upcoming: Boolean,
    override val launch_year: Int?,
    override val launch_date_unix: Int?,
    override val launch_date_utc: String?,
    override val is_tentative: Boolean,
    override val tentative_max_precision: String?,
    override val tbd: Boolean = false,
    override val launch_window: Int?,
    override val rocket: RocketResponseImpl?,
    override val ships: List<String>?,
    override val launch_site: LaunchSiteResponseImpl?,
    override val launch_success: Boolean,
    override val launch_failure_details: LaunchFailureDetailsResponseImpl?,
    override val links: LinksResponseImpl?,
    override val details: String?,
    override val static_fire_date_utc: String?,
    override val static_fire_date_unix: Int?
) : LaunchResponse<RocketResponseImpl, LaunchSiteResponseImpl, LaunchFailureDetailsResponseImpl, LinksResponseImpl>
