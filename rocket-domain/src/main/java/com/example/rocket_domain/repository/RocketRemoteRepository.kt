package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.RocketAlias
import com.example.rocket_domain.model.remote.RocketResponseAlias

interface RocketRemoteRepository<T: RocketResponseAlias, M: RocketAlias> {

    fun responseToModel(rocketResponse: T): M
}