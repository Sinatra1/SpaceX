package com.example.rocket_data.model.local

import com.example.rocket_domain.model.local.Payload

data class PayloadImpl(
    override var id: String?,
    override var norad_id: List<String>?,
    override var reused: Boolean,
    override var customers: List<String>?,
    override var nationality: String?,
    override var manufacturer: String?,
    override var type: String?,
    override var mass_kg: Float?,
    override var mass_lbs: Float?,
    override var orbit: String?,
    override var orbit_params: OrbitParamsImpl?
) : Payload<OrbitParamsImpl>