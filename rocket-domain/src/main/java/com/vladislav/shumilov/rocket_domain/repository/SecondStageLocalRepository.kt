package com.vladislav.shumilov.rocket_domain.repository

import com.vladislav.shumilov.rocket_domain.model.local.SecondStage

interface SecondStageLocalRepository {
    fun insertList(secondStages: List<SecondStage>)

    suspend fun getList(): List<SecondStage>
}