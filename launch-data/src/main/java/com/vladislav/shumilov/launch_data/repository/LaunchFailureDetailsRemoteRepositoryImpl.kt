package com.vladislav.shumilov.launch_data.repository

import com.vladislav.shumilov.core_data.util.generateRandomId
import com.vladislav.shumilov.launch_data.model.local.LaunchFailureDetailsImpl
import com.vladislav.shumilov.launch_data.model.remote.LaunchFailureDetailsResponseImpl
import com.vladislav.shumilov.launch_domain.repository.LaunchFailureDetailsRemoteRepository

class LaunchFailureDetailsRemoteRepositoryImpl:
    LaunchFailureDetailsRemoteRepository<LaunchFailureDetailsResponseImpl, LaunchFailureDetailsImpl> {

    override fun responseToModel(
        launchFailureDetailsResponse: LaunchFailureDetailsResponseImpl,
        launchId: String
    ) =
        LaunchFailureDetailsImpl(
            generateId(),
            launchId,
            launchFailureDetailsResponse.time,
            launchFailureDetailsResponse.altitude,
            launchFailureDetailsResponse.reason
        )

    override fun generateId() = generateRandomId()
}