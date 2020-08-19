package com.example.rocket_data.repository

import com.example.rocket_data.model.local.SecondStageImpl
import com.example.rocket_domain.model.remote.SecondStageResponse
import com.example.rocket_domain.repository.PayloadRemoteRepository
import com.example.rocket_domain.repository.SecondStageRemoteRepository
import com.vladislav.shumilov.core_data.util.generateRandomId
import javax.inject.Inject

class SecondStageRemoteRepositoryImpl @Inject constructor(
    private val payloadRemoteRepository: PayloadRemoteRepository
) :
    SecondStageRemoteRepository {

    override fun responseToModel(secondStageResponse: SecondStageResponse, rocketId: String) =
        SecondStageImpl(
            generateId(),
            rocketId,
            secondStageResponse.block
        ).apply {
            payloads = secondStageResponse.payloads?.let(payloadRemoteRepository::responseToModels)
        }

    override fun generateId() = generateRandomId()
}