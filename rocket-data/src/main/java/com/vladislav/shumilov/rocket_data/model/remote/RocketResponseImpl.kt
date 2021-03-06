package com.vladislav.shumilov.rocket_data.model.remote

import com.vladislav.shumilov.rocket_domain.model.remote.RocketResponse

class RocketResponseImpl(
    override val rocket_id: String,
    override val rocket_name: String?,
    override val rocket_type: String?,
    override val first_stage: FirstStageResponseImpl?,
    override val second_stage: SecondStageResponseImpl?,
    override val fairings: FairingsResponseImpl?
) : RocketResponse