package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.Rocket
import com.example.rocket_domain.model.remote.RocketResponse

interface RocketRemoteRepository {

    fun responseToModel(rocketResponse: RocketResponse): Rocket
}