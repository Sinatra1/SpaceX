package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.RocketAlias
import io.reactivex.Single

interface RocketLocalRepository<T: RocketAlias> {
    fun remoteListToLocalList(rocketResponses: List<T>)

    fun remoteModelToLocalModel(rocketResponse: T)

    fun getList(): Single<List<T>>
}