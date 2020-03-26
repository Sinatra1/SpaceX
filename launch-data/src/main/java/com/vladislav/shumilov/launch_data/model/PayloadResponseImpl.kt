package com.vladislav.shumilov.launch_data.model

import com.vladislav.shumilov.launch_domain.model.PayloadResponse

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
) : PayloadResponse<OrbitParamsResponseImpl>