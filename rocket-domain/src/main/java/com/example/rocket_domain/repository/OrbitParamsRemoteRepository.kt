package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.OrbitParams
import com.example.rocket_domain.model.remote.OrbitParamsResponse

interface OrbitParamsRemoteRepository<T: OrbitParamsResponse, M: OrbitParams> {

    fun responseToModels(orbitParamsResponses: List<T>, payloadId: String): List<M>

    fun responseToModel(orbitParamsResponse: T, payloadId: String): M

    fun generateId(): String
}