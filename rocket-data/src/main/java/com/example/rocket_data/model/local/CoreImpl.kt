package com.example.rocket_data.model.local

import com.example.rocket_domain.model.local.Core

class CoreImpl(
    override var serial: String?,
    override var flight: Int?,
    override var block: String?,
    override var gridfins: Boolean,
    override var legs: Boolean,
    override var reused: Boolean,
    override var land_success: Boolean?,
    override var landing_intent: Boolean,
    override var landing_type: String?,
    override var landing_vehicle: String?
) : Core