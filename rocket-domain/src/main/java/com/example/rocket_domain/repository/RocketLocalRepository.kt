package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.RocketAlias
import io.reactivex.Single

typealias RocketLocalRepositoryAlias = RocketLocalRepository<RocketAlias>

interface RocketLocalRepository<T : RocketAlias> {
    fun insertList(rockets: List<T>)

    fun getList(): Single<List<T>>
}