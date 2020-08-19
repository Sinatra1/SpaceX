package com.vladislav.shumilov.mission_data.model.remote

import com.vladislav.shumilov.mission_domain.model.remote.MissionResponse

class MissionResponseImpl(
    override val name: String?,
    override val flight: String?
) : MissionResponse