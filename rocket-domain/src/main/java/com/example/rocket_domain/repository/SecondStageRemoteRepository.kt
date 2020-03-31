package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.SecondStage
import com.example.rocket_domain.model.remote.SecondStageResponse

interface SecondStageRemoteRepository<T: SecondStageResponse<*>, M: SecondStage<*>> {

    fun responseToModel(secondStageResponse: T, rocketId: String): M

    fun generateId(): String
}