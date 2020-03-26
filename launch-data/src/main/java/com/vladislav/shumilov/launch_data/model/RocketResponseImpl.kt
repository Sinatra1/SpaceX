package com.vladislav.shumilov.launch_data.model

import com.vladislav.shumilov.launch_domain.model.RocketResponse

class RocketResponseImpl(
    override val rocket_id: String?,
    override val rocket_name: String?,
    override val rocket_type: String?,
    override val first_stage: FirstStageResponseImpl?,
    override val second_stage: SecondStageResponseImpl?,
    override val fairings: FairingsResponseImpl?
) : RocketResponse<FirstStageResponseImpl, SecondStageResponseImpl, FairingsResponseImpl>