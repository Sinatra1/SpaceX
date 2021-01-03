package com.vladislav.shumilov.launch_data.repository

import com.vladislav.shumilov.core_data.util.generateRandomId
import com.vladislav.shumilov.launch_data.model.local.LaunchFailureDetailsImpl
import com.vladislav.shumilov.launch_domain.model.remote.LaunchFailureDetailsResponse
import com.vladislav.shumilov.launch_domain.repository.LaunchFailureDetailsRemoteRepository
import javax.inject.Inject

class LaunchFailureDetailsRemoteRepositoryImpl @Inject constructor():
    LaunchFailureDetailsRemoteRepository {

    override fun responseToModel(
        launchFailureDetailsResponse: LaunchFailureDetailsResponse,
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