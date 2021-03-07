package com.vladislav.shumilov.rocket_data.model.remote

import com.vladislav.shumilov.rocket_domain.model.remote.CoreResponse

class CoreResponseImpl(
    override val core_serial: String,
    override val flight: Int?,
    override val block: String?,
    override val gridfins: Boolean,
    override val legs: Boolean,
    override val reused: Boolean,
    override val land_success: Boolean?,
    override val landing_intent: Boolean,
    override val landing_type: String?,
    override val landing_vehicle: String?
) : CoreResponse