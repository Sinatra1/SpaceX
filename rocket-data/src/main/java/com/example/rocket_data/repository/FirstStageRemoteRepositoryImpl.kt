package com.example.rocket_data.repository

import com.example.rocket_data.model.local.FirstStageImpl
import com.example.rocket_data.model.remote.FirstStageResponseImpl
import com.example.rocket_domain.repository.FirstStageRemoteRepository
import com.vladislav.shumilov.core_data.util.generateRandomId

class FirstStageRemoteRepositoryImpl :
    FirstStageRemoteRepository<FirstStageResponseImpl, FirstStageImpl> {

    override fun responseToModel(firstStageResponse: FirstStageResponseImpl, reportId: String) =
        FirstStageImpl(generateId(), reportId)

    override fun generateId() = generateRandomId()
}