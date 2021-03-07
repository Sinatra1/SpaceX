package com.vladislav.shumilov.rocket_domain.repository

import com.vladislav.shumilov.rocket_domain.model.local.OrbitParams
import io.reactivex.Single

interface OrbitParamsLocalRepository {
    fun insertList(orbitParams: List<OrbitParams>)

    fun getList(): Single<List<OrbitParams>>
}