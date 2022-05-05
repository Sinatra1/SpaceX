package com.vladislav.shumilov.rocket_domain.repository

import com.vladislav.shumilov.rocket_domain.model.local.Rocket

interface RocketLocalRepository {
    fun insertList(rockets: List<Rocket>)

    suspend fun getList(): List<Rocket>
}