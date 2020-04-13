package com.example.rocket_domain.repository

import com.example.rocket_domain.model.local.Core
import com.example.rocket_domain.model.remote.CoreResponse

typealias CoreRemoteRepositoryAlias = CoreRemoteRepository<CoreResponse, Core>

interface CoreRemoteRepository<T: CoreResponse, M: Core> {

    fun responseToModels(coreResponses: List<T>): List<M>

    fun responseToModel(coreResponse: T): M

    fun generateId(): String
}