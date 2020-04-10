package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.FirstStage
import io.reactivex.Single

interface FirstStageLocalRepository<T : FirstStage<*>> {
    fun insertList(firstStages: List<T>)

    fun getList(): Single<List<T>>
}