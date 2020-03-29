package com.example.rocket_data.repository

import com.example.rocket_data.model.local.FairingsImpl
import com.example.rocket_data.model.remote.FairingsResponseImpl
import com.example.rocket_domain.repository.FairingsRemoteRepository
import com.vladislav.shumilov.core_data.util.generateRandomId

class FairingsRemoteRepositoryImpl:
    FairingsRemoteRepository<FairingsResponseImpl, FairingsImpl> {

    override fun responseToModel(fairingsResponse: FairingsResponseImpl, reportId: String) =
        FairingsImpl(
            generateId(),
            reportId,
            fairingsResponse.reused,
            fairingsResponse.recovery_attempt,
            fairingsResponse.recovered,
            fairingsResponse.ship)

    override fun generateId() = generateRandomId()
}