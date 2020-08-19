package com.example.rocket_data.repository

import com.example.rocket_data.model.local.FirstStageImpl
import com.example.rocket_domain.model.remote.FirstStageResponse
import com.example.rocket_domain.repository.CoreRemoteRepository
import com.example.rocket_domain.repository.FirstStageRemoteRepository
import com.vladislav.shumilov.core_data.util.generateRandomId
import javax.inject.Inject

class FirstStageRemoteRepositoryImpl @Inject constructor(
    private val coreRemoteRepository: CoreRemoteRepository
) :
    FirstStageRemoteRepository {

    override fun responseToModel(
        firstStageResponse: FirstStageResponse,
        rocketId: String
    ) =
        FirstStageImpl(generateId(), rocketId).apply {
            cores = prepareCores(firstStageResponse)
        }

    private fun prepareCores(firstStageResponse: FirstStageResponse) =
        firstStageResponse.cores?.let {
            coreRemoteRepository.responseToModels(it)
        }


    override fun generateId() = generateRandomId()
}