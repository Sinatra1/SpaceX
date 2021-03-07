package com.vladislav.shumilov.rocket_domain.repository

import com.vladislav.shumilov.rocket_domain.model.local.SecondStage
import com.vladislav.shumilov.rocket_domain.model.remote.SecondStageResponse

interface SecondStageRemoteRepository {

    fun responseToModel(secondStageResponse: SecondStageResponse, rocketId: String): SecondStage

    fun generateId(): String
}