package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.FirstStage
import com.example.rocket_domain.model.local.FirstStageAlias
import com.example.rocket_domain.model.remote.FirstStageResponse
import com.example.rocket_domain.model.remote.FirstStageResponseAlias

typealias FirstStageRemoteRepositoryAlias = FirstStageRemoteRepository<FirstStageResponseAlias, FirstStageAlias>

interface FirstStageRemoteRepository<T: FirstStageResponse<*>, M: FirstStage<*>> {

    fun responseToModel(firstStageResponse: T, rocketId: String): M

    fun generateId(): String
}