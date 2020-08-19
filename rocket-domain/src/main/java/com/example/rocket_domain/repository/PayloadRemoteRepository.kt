package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.Payload
import com.example.rocket_domain.model.remote.PayloadResponse

interface PayloadRemoteRepository {

    fun responseToModels(payloadResponses: List<PayloadResponse>): List<Payload>

    fun responseToModel(payloadResponse: PayloadResponse): Payload

    fun generateId(): String
}