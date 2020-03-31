package com.example.rocket_data.repository

import com.example.rocket_data.model.local.SecondStageImpl
import com.example.rocket_data.model.remote.SecondStageResponseImpl
import com.example.rocket_domain.repository.SecondStageRemoteRepository
import com.vladislav.shumilov.core_data.util.generateRandomId

class SecondStageRemoteRepositoryImpl:
    SecondStageRemoteRepository<SecondStageResponseImpl, SecondStageImpl> {

    override fun responseToModel(secondStageResponse: SecondStageResponseImpl, rocketId: String) =
        SecondStageImpl(generateId(), rocketId, secondStageResponse.block)

    override fun generateId() = generateRandomId()
}