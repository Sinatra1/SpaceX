package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.Fairings
import com.example.rocket_domain.model.remote.FairingsResponse

typealias FairingsRemoteRepositoryAlias = FairingsRemoteRepository<FairingsResponse, Fairings>

interface FairingsRemoteRepository<T: FairingsResponse, M: Fairings> {

    fun responseToModel(fairingsResponse: T, rocketId: String): M

    fun generateId(): String
}