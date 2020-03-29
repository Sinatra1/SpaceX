package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.local.LaunchFailureDetails
import com.vladislav.shumilov.launch_domain.model.remote.LaunchFailureDetailsResponse

interface LaunchFailureDetailsRemoteRepository<T: LaunchFailureDetailsResponse, M: LaunchFailureDetails> {

    fun responseToModel(launchFailureDetailsResponse: T, launchId: String): M

    fun generateId(): String
}