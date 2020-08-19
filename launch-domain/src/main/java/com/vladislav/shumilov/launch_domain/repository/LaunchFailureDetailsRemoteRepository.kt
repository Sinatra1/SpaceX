package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.local.LaunchFailureDetails
import com.vladislav.shumilov.launch_domain.model.remote.LaunchFailureDetailsResponse

interface LaunchFailureDetailsRemoteRepository {

    fun responseToModel(
        launchFailureDetailsResponse: LaunchFailureDetailsResponse,
        launchId: String
    ): LaunchFailureDetails

    fun generateId(): String
}