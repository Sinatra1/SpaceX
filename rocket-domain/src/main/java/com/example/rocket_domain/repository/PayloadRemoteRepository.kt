package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.Payload
import com.example.rocket_domain.model.remote.PayloadResponse

typealias PayloadRemoteRepositoryAlias = PayloadRemoteRepository<PayloadResponse<*>, Payload<*>>

interface PayloadRemoteRepository<T: PayloadResponse<*>, M: Payload<*>> {

    fun responseToModels(payloadResponses: List<T>): List<M>

    fun responseToModel(payloadResponse: T): M

    fun generateId(): String
}