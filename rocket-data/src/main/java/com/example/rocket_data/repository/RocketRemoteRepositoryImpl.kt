package com.example.rocket_data.repository

import com.example.rocket_data.model.local.RocketImpl
import com.example.rocket_data.model.remote.RocketResponseImpl
import com.example.rocket_domain.repository.RocketRemoteRepository
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.generateRandomId
import javax.inject.Inject

@FragmentScope
class RocketRemoteRepositoryImpl @Inject constructor(
    private val firstStageRemoteRepository: FirstStageRemoteRepositoryImpl,
    private val secondStageRemoteRepository: SecondStageRemoteRepositoryImpl,
    private val fairingsRemoteRepository: FairingsRemoteRepositoryImpl
) :
    RocketRemoteRepository<RocketResponseImpl, RocketImpl> {

    override fun responseToModel(rocketResponse: RocketResponseImpl): RocketImpl {
        val reportId = generateId()

        return RocketImpl(
            reportId,
            rocketResponse.rocket_name,
            rocketResponse.rocket_type,
            rocketResponse.first_stage?.let {
                firstStageRemoteRepository.responseToModel(
                    it,
                    reportId
                )
            },
            rocketResponse.second_stage?.let {
                secondStageRemoteRepository.responseToModel(
                    it,
                    reportId
                )
            },
            rocketResponse.fairings?.let { fairingsRemoteRepository.responseToModel(it, reportId) }
        )
    }

    override fun generateId() = generateRandomId()
}