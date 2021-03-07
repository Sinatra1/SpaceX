package com.vladislav.shumilov.rocket_domain.repository

import com.vladislav.shumilov.rocket_domain.model.local.FirstStage
import io.reactivex.Single

interface FirstStageLocalRepository {
    fun insertList(firstStages: List<FirstStage>)

    fun getList(): Single<List<FirstStage>>
}