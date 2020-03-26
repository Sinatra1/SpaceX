package com.vladislav.shumilov.launch_data.model

import com.vladislav.shumilov.launch_domain.model.FirstStageResponse

class FirstStageResponseImpl : FirstStageResponse<CoreResponseImpl> {
    override val cores: List<CoreResponseImpl>? = null
}