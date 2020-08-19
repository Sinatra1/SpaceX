package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.OrbitParams
import io.reactivex.Single

interface OrbitParamsLocalRepository {
    fun insertList(orbitParams: List<OrbitParams>)

    fun getList(): Single<List<OrbitParams>>
}