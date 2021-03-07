package com.vladislav.shumilov.rocket_domain.repository

import com.vladislav.shumilov.rocket_domain.model.local.Payload
import com.vladislav.shumilov.rocket_domain.model.remote.PayloadResponse

interface PayloadRemoteRepository {

    fun responseToModels(payloadResponses: List<PayloadResponse>): List<Payload>

    fun responseToModel(payloadResponse: PayloadResponse): Payload

    fun generateId(): String
}