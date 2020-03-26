package com.vladislav.shumilov.launch_data.model

import com.vladislav.shumilov.launch_domain.model.FairingsResponse

class FairingsResponseImpl(
    override val reused: Boolean,
    override val recovery_attempt: Boolean,
    override val recovered: Boolean,
    override val ship: String?
) : FairingsResponse