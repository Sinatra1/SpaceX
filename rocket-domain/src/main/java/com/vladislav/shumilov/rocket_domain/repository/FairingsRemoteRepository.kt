package com.vladislav.shumilov.rocket_domain.repository

import com.vladislav.shumilov.rocket_domain.model.local.Fairings
import com.vladislav.shumilov.rocket_domain.model.remote.FairingsResponse

interface FairingsRemoteRepository {

    fun responseToModel(fairingsResponse: FairingsResponse, rocketId: String): Fairings

    fun generateId(): String
}