package com.example.rocket_data.repository

import com.example.rocket_data.model.local.SecondStageImpl
import com.example.rocket_data.model.remote.SecondStageResponseImpl
import com.example.rocket_domain.repository.SecondStageRemoteRepository
import com.vladislav.shumilov.core_data.util.generateRandomId
import javax.inject.Inject

class SecondStageRemoteRepositoryImpl @Inject constructor(
    private val payloadRemoteRepository: PayloadRemoteRepositoryImpl
) :
    SecondStageRemoteRepository<SecondStageResponseImpl, SecondStageImpl> {

    override fun responseToModel(secondStageResponse: SecondStageResponseImpl, rocketId: String) =
        SecondStageImpl(
            generateId(),
            rocketId,
            secondStageResponse.block
        ).apply {
            payloads = secondStageResponse.payloads?.let {
                payloadRemoteRepository.responseToModels(it)
            }
        }

    override fun generateId() = generateRandomId()
}