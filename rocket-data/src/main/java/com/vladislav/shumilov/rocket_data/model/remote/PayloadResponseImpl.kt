package com.vladislav.shumilov.rocket_data.model.remote

import com.vladislav.shumilov.rocket_domain.model.remote.PayloadResponse

class PayloadResponseImpl(
    override val payload_id: String?,
    override val norad_id: List<String>?,
    override val reused: Boolean,
    override val customers: List<String>?,
    override val nationality: String?,
    override val manufacturer: String?,
    override val payload_type: String?,
    override val payload_mass_kg: Float?,
    override val payload_mass_lbs: Float?,
    override val orbit: String?,
    override val orbit_params: OrbitParamsResponseImpl?
) : PayloadResponse