package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.OrbitParams
import com.example.rocket_domain.model.remote.OrbitParamsResponse

interface OrbitParamsRemoteRepository {

    fun responseToModels(
        orbitParamsResponses: List<OrbitParamsResponse>,
        payloadId: String
    ): List<OrbitParams>

    fun responseToModel(orbitParamsResponse: OrbitParamsResponse, payloadId: String): OrbitParams

    fun generateId(): String
}