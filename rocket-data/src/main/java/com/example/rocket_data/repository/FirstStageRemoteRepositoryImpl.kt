package com.example.rocket_data.repository

import com.example.rocket_data.model.local.FirstStageImpl
import com.example.rocket_data.model.remote.FirstStageResponseImpl
import com.example.rocket_domain.repository.FirstStageRemoteRepository
import com.vladislav.shumilov.core_data.util.generateRandomId
import javax.inject.Inject

class FirstStageRemoteRepositoryImpl @Inject constructor(
    private val coreRemoteRepository: CoreRemoteRepositoryImpl
) :
    FirstStageRemoteRepository<FirstStageResponseImpl, FirstStageImpl> {

    override fun responseToModel(
        firstStageResponse: FirstStageResponseImpl,
        rocketId: String
    ) = FirstStageImpl(generateId(), rocketId).apply {
        cores = prepareCores(firstStageResponse)
    }

    private fun prepareCores(firstStageResponse: FirstStageResponseImpl) =
        firstStageResponse.cores?.let {
            coreRemoteRepository.responseToModels(it)
        }


    override fun generateId() = generateRandomId()
}