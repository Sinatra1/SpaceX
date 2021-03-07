package com.vladislav.shumilov.rocket_data.model.remote

import com.vladislav.shumilov.rocket_domain.model.remote.SecondStageResponse

class SecondStageResponseImpl(
    override val block: Int?,
    override val payloads: List<PayloadResponseImpl>?
) : SecondStageResponse