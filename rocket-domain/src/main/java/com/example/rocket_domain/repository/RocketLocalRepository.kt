package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.RocketAlias
import io.reactivex.Single

interface RocketLocalRepository<T : RocketAlias> {
    fun insertList(launches: List<T>)

    fun getList(): Single<List<T>>
}