package com.example.rocket_data.model.remote

import com.example.rocket_domain.model.remote.FirstStageResponse

class FirstStageResponseImpl :
    FirstStageResponse<CoreResponseImpl> {
    override val cores: List<CoreResponseImpl>? = null
}