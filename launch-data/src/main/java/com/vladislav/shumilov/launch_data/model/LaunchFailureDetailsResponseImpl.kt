package com.vladislav.shumilov.launch_data.model

import com.vladislav.shumilov.launch_domain.model.LaunchFailureDetailsResponse

class LaunchFailureDetailsResponseImpl(
    override val time: Int?,
    override val altitude: String?,
    override val reason: String?
) : LaunchFailureDetailsResponse