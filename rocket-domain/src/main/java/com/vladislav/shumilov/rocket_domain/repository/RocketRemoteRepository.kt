package com.vladislav.shumilov.rocket_domain.repository

import com.vladislav.shumilov.rocket_domain.model.local.Rocket
import com.vladislav.shumilov.rocket_domain.model.remote.RocketResponse

interface RocketRemoteRepository {

    fun responseToModel(rocketResponse: RocketResponse): Rocket
}