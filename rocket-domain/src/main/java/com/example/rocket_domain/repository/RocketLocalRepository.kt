package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.Rocket
import io.reactivex.Single

interface RocketLocalRepository {
    fun insertList(rockets: List<Rocket>)

    fun getList(): Single<List<Rocket>>
}