package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.local.Launch
import com.vladislav.shumilov.launch_domain.model.remote.LaunchResponse
import io.reactivex.Single

interface LaunchRemoteRepository {
    suspend fun getList(offset: Int, limit: Int): List<LaunchResponse>

    fun responsesToModels(launchResponses: List<LaunchResponse>): List<Launch>

    fun responseToModel(launchResponse: LaunchResponse): Launch

    fun generateId(launchResponse: LaunchResponse): String
}