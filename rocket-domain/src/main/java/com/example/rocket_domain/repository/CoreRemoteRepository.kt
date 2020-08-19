package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.Core
import com.example.rocket_domain.model.remote.CoreResponse

interface CoreRemoteRepository {

    fun responseToModels(coreResponses: List<CoreResponse>): List<Core>

    fun responseToModel(coreResponse: CoreResponse): Core

    fun generateId(): String
}