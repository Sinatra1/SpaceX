package com.example.rocket_data.repository

import com.example.rocket_data.model.local.OrbitParamsImpl
import com.example.rocket_domain.model.local.OrbitParams
import com.example.rocket_domain.model.remote.OrbitParamsResponse
import com.example.rocket_domain.repository.OrbitParamsRemoteRepository
import com.vladislav.shumilov.core_data.util.generateRandomId
import javax.inject.Inject

class OrbitParamsRemoteRepositoryImpl @Inject constructor():
    OrbitParamsRemoteRepository {

    override fun responseToModels(
        orbitParamsResponses: List<OrbitParamsResponse>,
        payloadId: String
    ): List<OrbitParams> {
        val orbitParams = mutableListOf<OrbitParams>()

        orbitParamsResponses.forEach {
            orbitParams.add(responseToModel(it, payloadId))
        }

        return orbitParams
    }

    override fun responseToModel(orbitParamsResponse: OrbitParamsResponse, payloadId: String) =
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