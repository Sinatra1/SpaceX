package com.example.rocket_data.repository

import com.example.rocket_data.model.local.RocketImpl
import com.example.rocket_data.model.remote.RocketResponseImpl
import com.example.rocket_domain.repository.RocketRemoteRepository
import com.vladislav.shumilov.core_data.FragmentScope
import javax.inject.Inject

@FragmentScope
class RocketRemoteRepositoryImpl @Inject constructor(
    private val firstStageRemoteRepository: FirstStageRemoteRepositoryImpl,
    private val secondStageRemoteRepository: SecondStageRemoteRepositoryImpl,
    private val fairingsRemoteRepository: FairingsRemoteRepositoryImpl
) :
    RocketRemoteRepository<RocketResponseImpl, RocketImpl> {

    override fun responseToModel(rocketResponse: RocketResponseImpl): RocketImpl {

        val rocket = RocketImpl(
            rocketResponse.rocket_id,
            rocketResponse.rocket_name,
            rocketResponse.rocket_type
        )

        rocket.first_stage = rocketResponse.first_stage?.let {
            firstStageRemoteRepository.responseToModel(
                it,
                rocketResponse.rocket_id
            )
        }

        rocket.second_stage = rocketResponse.second_stage?.let {
            secondStageRemoteRepository.responseToModel(
                it,
                rocketResponse.rocket_id
            )
        }

        rocket.fairings =
            rocketResponse.fairings?.let {
                fairingsRemoteRepository.responseToModel(
                    it,
                    rocketResponse.rocket_id
                )
            }

        return rocket
    }
}