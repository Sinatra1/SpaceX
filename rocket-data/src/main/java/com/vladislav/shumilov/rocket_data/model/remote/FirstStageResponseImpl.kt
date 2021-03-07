package com.vladislav.shumilov.rocket_data.model.remote

import com.vladislav.shumilov.rocket_domain.model.remote.FirstStageResponse

class FirstStageResponseImpl :
    FirstStageResponse {
    override val cores: List<CoreResponseImpl>? = null
}