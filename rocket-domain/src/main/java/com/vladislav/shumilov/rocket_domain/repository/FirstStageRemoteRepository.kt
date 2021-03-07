package com.vladislav.shumilov.rocket_domain.repository

import com.vladislav.shumilov.rocket_domain.model.local.FirstStage
import com.vladislav.shumilov.rocket_domain.model.remote.FirstStageResponse

interface FirstStageRemoteRepository {

    fun responseToModel(firstStageResponse: FirstStageResponse, rocketId: String): FirstStage

    fun generateId(): String
}