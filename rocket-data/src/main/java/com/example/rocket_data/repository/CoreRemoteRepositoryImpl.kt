package com.example.rocket_data.repository

import com.example.rocket_data.model.local.CoreImpl
import com.example.rocket_data.model.remote.CoreResponseImpl
import com.example.rocket_domain.repository.CoreRemoteRepository
import com.vladislav.shumilov.core_data.util.generateRandomId

class CoreRemoteRepositoryImpl : CoreRemoteRepository<CoreResponseImpl, CoreImpl> {

    override fun responseToModels(coreResponses: List<CoreResponseImpl>): List<CoreImpl> {
        val cores = ArrayList<CoreImpl>()

        coreResponses.forEach {
            cores.add(responseToModel(it))
        }

        return cores
    }

    override fun responseToModel(coreResponse: CoreResponseImpl) =
        CoreImpl(
            generateId(),
            coreResponse.core_serial,
            coreResponse.flight,
            coreResponse.block,
            coreResponse.gridfins,
            coreResponse.legs,
            coreResponse.reused,
            coreResponse.land_success,
            coreResponse.landing_intent,
            coreResponse.landing_type,
            coreResponse.landing_vehicle
        )

    override fun generateId() = generateRandomId()

}