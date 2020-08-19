package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.SecondStage
import io.reactivex.Single

interface SecondStageLocalRepository {
    fun insertList(secondStages: List<SecondStage>)

    fun getList(): Single<List<SecondStage>>
}