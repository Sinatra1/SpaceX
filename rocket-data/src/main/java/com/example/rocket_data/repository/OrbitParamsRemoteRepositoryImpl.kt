package com.example.rocket_data.repository

import com.example.rocket_data.model.local.OrbitParamsImpl
import com.example.rocket_data.model.remote.OrbitParamsResponseImpl
import com.example.rocket_domain.repository.OrbitParamsRemoteRepository
import com.vladislav.shumilov.core_data.util.generateRandomId

class OrbitParamsRemoteRepositoryImpl :
    OrbitParamsRemoteRepository<OrbitParamsResponseImpl, OrbitParamsImpl> {

    override fun responseToModels(orbitParamsResponses: List<OrbitParamsResponseImpl>, payloadId: String): List<OrbitParamsImpl> {
        val orbitParams = ArrayList<OrbitParamsImpl>()

        orbitParamsResponses.forEach {
            orbitParams.add(responseToModel(it, payloadId))
        }

        return orbitParams
    }

    override fun responseToModel(orbitParamsResponse: OrbitParamsResponseImpl, payloadId: String) =
        OrbitParamsImpl(
            generateId(),
            payloadId,
            orbitParamsResponse.reference_system,
            orbitParamsResponse.regime,
            orbitParamsResponse.longitude,
            orbitParamsResponse.semi_major_axis_km,
            orbitParamsResponse.eccentricity,
            orbitParamsResponse.periapsis_km,
            orbitParamsResponse.apoapsis_km,
            orbitParamsResponse.inclination_deg,
            orbitParamsResponse.period_min,
            orbitParamsResponse.lifespan_years,
            orbitParamsResponse.epoch,
            orbitParamsResponse.mean_motion,
            orbitParamsResponse.raan,
            orbitParamsResponse.arg_of_pericenter,
            orbitParamsResponse.mean_anomaly
        )

    override fun generateId() = generateRandomId()

}