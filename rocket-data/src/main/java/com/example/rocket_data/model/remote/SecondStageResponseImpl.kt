package com.example.rocket_data.model.remote

import com.example.rocket_domain.model.remote.SecondStageResponse

class SecondStageResponseImpl(
    override val block: Int?,
    override val payloads: List<PayloadResponseImpl>?
) : SecondStageResponse