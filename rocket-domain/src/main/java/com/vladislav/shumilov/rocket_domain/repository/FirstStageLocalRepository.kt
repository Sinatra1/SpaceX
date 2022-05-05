package com.vladislav.shumilov.rocket_domain.repository

import com.vladislav.shumilov.rocket_domain.model.local.FirstStage

interface FirstStageLocalRepository {
    fun insertList(firstStages: List<FirstStage>)

    suspend fun getList(): List<FirstStage>
}