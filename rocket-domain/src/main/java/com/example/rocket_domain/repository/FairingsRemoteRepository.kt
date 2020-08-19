package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.Fairings
import com.example.rocket_domain.model.remote.FairingsResponse

interface FairingsRemoteRepository {

    fun responseToModel(fairingsResponse: FairingsResponse, rocketId: String): Fairings

    fun generateId(): String
}