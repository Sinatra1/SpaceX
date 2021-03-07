package com.vladislav.shumilov.rocket_data.repository

import com.vladislav.shumilov.rocket_data.model.local.FairingsImpl
import com.vladislav.shumilov.rocket_domain.model.remote.FairingsResponse
import com.vladislav.shumilov.rocket_domain.repository.FairingsRemoteRepository
import com.vladislav.shumilov.core_data.util.generateRandomId
import javax.inject.Inject

class FairingsRemoteRepositoryImpl @Inject constructor():
    FairingsRemoteRepository {

    override fun responseToModel(fairingsResponse: FairingsResponse, rocketId: String) =
        FairingsImpl(
            generateId(),
            rocketId,
            fairingsResponse.reused,
            fairingsResponse.recovery_attempt,
            fairingsResponse.recovered,
            fairingsResponse.ship
        )

    override fun generateId() = generateRandomId()
}