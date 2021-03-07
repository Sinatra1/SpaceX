package com.vladislav.shumilov.rocket_data.model.remote

import com.vladislav.shumilov.rocket_domain.model.remote.FairingsResponse

class FairingsResponseImpl(
    override val reused: Boolean,
    override val recovery_attempt: Boolean,
    override val recovered: Boolean,
    override val ship: String?
) : FairingsResponse