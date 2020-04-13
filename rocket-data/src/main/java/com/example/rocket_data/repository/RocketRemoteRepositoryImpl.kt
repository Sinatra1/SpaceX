package com.example.rocket_data.repository

import com.example.rocket_data.model.local.FairingsImpl
import com.example.rocket_data.model.local.FirstStageImpl
import com.example.rocket_data.model.local.RocketImpl
import com.example.rocket_data.model.local.SecondStageImpl
import com.example.rocket_data.model.remote.RocketResponseImpl
import com.example.rocket_domain.repository.FairingsRemoteRepositoryAlias
import com.example.rocket_domain.repository.FirstStageRemoteRepositoryAlias
import com.example.rocket_domain.repository.RocketRemoteRepository
import com.example.rocket_domain.repository.SecondStageRemoteRepositoryAlias
import com.vladislav.shumilov.core_data.FragmentScope
import javax.inject.Inject

@FragmentScope
class RocketRemoteRepositoryImpl @Inject constructor(
    private val firstStageRemoteRepository: FirstStageRemoteRepositoryAlias,
    private val secondStageRemoteRepository: SecondStageRemoteRepositoryAlias,
    private val fairingsRemoteRepository: FairingsRemoteRepositoryAlias
) :
    RocketRemoteRepository<RocketResponseImpl, RocketImpl> {

    override fun responseToModel(rocketResponse: RocketResponseImpl) =
        RocketImpl(
            rocketResponse.rocket_id,
            rocketResponse.rocket_name,
            rocketResponse.rocket_type
        ).apply {
            first_stage = rocketResponse.first_stage?.let {
                firstStageRemoteRepository.responseToModel(
                    it,
                    rocketResponse.rocket_id
                ) as FirstStageImpl
            }

            second_stage = rocketResponse.second_stage?.let {
                secondStageRemoteRepository.responseToModel(
                    it,
                    rocketResponse.rocket_id
                ) as SecondStageImpl
            }

            fairings =
                rocketResponse.fairings?.let {
                    fairingsRemoteRepository.responseToModel(
                        it,
                        rocketResponse.rocket_id
                    ) as FairingsImpl
                }
        }

}