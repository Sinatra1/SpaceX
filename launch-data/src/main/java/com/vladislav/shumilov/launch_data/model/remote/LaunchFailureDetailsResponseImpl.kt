package com.vladislav.shumilov.launch_data.model.remote

import com.vladislav.shumilov.launch_domain.model.remote.LaunchFailureDetailsResponse

class LaunchFailureDetailsResponseImpl(
    override val time: Int?,
    override val altitude: String?,
    override val reason: String?
) : LaunchFailureDetailsResponse