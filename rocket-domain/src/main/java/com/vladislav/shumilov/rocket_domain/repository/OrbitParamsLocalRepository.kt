package com.vladislav.shumilov.rocket_domain.repository

import com.vladislav.shumilov.rocket_domain.model.local.OrbitParams

interface OrbitParamsLocalRepository {
    fun insertList(orbitParams: List<OrbitParams>)

    suspend fun getList(): List<OrbitParams>
}