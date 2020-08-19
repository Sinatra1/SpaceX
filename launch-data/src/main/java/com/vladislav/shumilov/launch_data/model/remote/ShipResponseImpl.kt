package com.vladislav.shumilov.launch_data.model.remote

import com.vladislav.shumilov.launch_domain.model.remote.ShipResponse
import com.vladislav.shumilov.mission_data.model.remote.MissionResponseImpl

class ShipResponseImpl(
    override val ship_id: String?,
    override val ship_name: String?,
    override val ship_model: String?,
    override val ship_type: String?,
    override val roles: List<String>?,
    override val active: Boolean,
    override val imo: Int?,
    override val mmsi: Int?,
    override val abs: Int?,
    override val weight_lbs: Int?,
    override val weight_kg: Int?,
    override val year_built: Int?,
    override val home_port: String?,
    override val status: String?,
    override val speed_kn: Int?,
    override val course_deg: Int?,
    override val latitude: Float?,
    override val longitude: Float?,
    override val successful_landings: Int?,
    override val attempted_landings: Int?,
    override val missions: List<MissionResponseImpl>?,
    override val url: String?,
    override val image: String?
) : ShipResponse