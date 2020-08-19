package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.SecondStage
import com.example.rocket_domain.model.remote.SecondStageResponse

interface SecondStageRemoteRepository {

    fun responseToModel(secondStageResponse: SecondStageResponse, rocketId: String): SecondStage

    fun generateId(): String
}