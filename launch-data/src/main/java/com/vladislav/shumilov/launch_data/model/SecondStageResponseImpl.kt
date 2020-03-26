package com.vladislav.shumilov.launch_data.model

import com.vladislav.shumilov.launch_domain.model.SecondStageResponse

class SecondStageResponseImpl(
    override val block: Int?,
    override val payloads: List<PayloadResponseImpl>?
) : SecondStageResponse<PayloadResponseImpl>