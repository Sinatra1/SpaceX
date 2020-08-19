package com.example.rocket_data.repository

import com.example.rocket_data.model.local.PayloadImpl
import com.example.rocket_domain.model.local.Payload
import com.example.rocket_domain.model.remote.PayloadResponse
import com.example.rocket_domain.repository.OrbitParamsRemoteRepository
import com.example.rocket_domain.repository.PayloadRemoteRepository
import com.vladislav.shumilov.core_data.util.generateRandomId
import javax.inject.Inject

class PayloadRemoteRepositoryImpl @Inject constructor(
    private val orbitParamsRemoteRepository: OrbitParamsRemoteRepository
) :
    PayloadRemoteRepository {

    override fun responseToModels(payloadResponses: List<PayloadResponse>): List<Payload> {
        val payloads = mutableListOf<Payload>()

        payloadResponses.forEach {
            payloads.add(responseToModel(it))
        }

        return payloads
    }

    override fun responseToModel(payloadResponse: PayloadResponse): Payload {
        val payloadId = generateId()

        return PayloadImpl(
            payloadId,
            payloadResponse.norad_id,
            payloadResponse.reused,
            payloadResponse.customers,
            payloadResponse.nationality,
            payloadResponse.manufacturer,
            payloadResponse.payload_type,
            payloadResponse.payload_mass_kg,
            payloadResponse.payload_mass_lbs,
            payloadResponse.orbit
        ).apply {
            orbitParams = payloadResponse.orbit_params?.let {
                orbitParamsRemoteRepository.responseToModel(it, payloadId)
            }
        }
    }

    override fun generateId() = generateRandomId()

}