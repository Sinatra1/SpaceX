package com.vladislav.shumilov.rocket_data.repository

import com.vladislav.shumilov.rocket_data.model.local.RocketImpl
import com.vladislav.shumilov.rocket_domain.model.remote.RocketResponse
import com.vladislav.shumilov.rocket_domain.repository.*
import com.vladislav.shumilov.core_data.FragmentScope
import javax.inject.Inject

@FragmentScope
class RocketRemoteRepositoryImpl @Inject constructor(
    private val firstStageRemoteRepository: FirstStageRemoteRepository,
    private val secondStageRemoteRepository: SecondStageRemoteRepository,
    private val fairingsRemoteRepository: FairingsRemoteRepository
) : RocketRemoteRepository {

    override fun responseToModel(rocketResponse: RocketResponse) =
        RocketImpl(
            rocketResponse.rocket_id,
            rocketResponse.rocket_name,
            rocketResponse.rocket_type
        ).apply {
            firstStage = rocketResponse.first_stage?.let {
                firstStageRemoteRepository.responseToModel(
                    it,
                    rocketResponse.rocket_id
                )
            }

            secondStage = rocketResponse.second_stage?.let {
                secondStageRemoteRepository.responseToModel(
                    it,
                    rocketResponse.rocket_id
                )
            }

            fairings = rocketResponse.fairings?.let {
                fairingsRemoteRepository.responseToModel(
                    it,
                    rocketResponse.rocket_id
                )
            }
        }

}