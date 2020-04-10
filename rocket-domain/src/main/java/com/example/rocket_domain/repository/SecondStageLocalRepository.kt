package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.SecondStage
import io.reactivex.Single

interface SecondStageLocalRepository<T : SecondStage<*>> {
    fun insertList(secondStages: List<T>)

    fun getList(): Single<List<T>>
}