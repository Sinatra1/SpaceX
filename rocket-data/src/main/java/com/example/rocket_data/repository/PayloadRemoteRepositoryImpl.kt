package com.example.rocket_data.repository

import com.example.rocket_data.model.local.OrbitParamsImpl
import com.example.rocket_data.model.local.PayloadImpl
import com.example.rocket_data.model.remote.PayloadResponseImpl
import com.example.rocket_domain.repository.OrbitParamsRemoteRepositoryAlias
import com.example.rocket_domain.repository.PayloadRemoteRepository
import com.vladislav.shumilov.core_data.util.generateRandomId
import javax.inject.Inject

class PayloadRemoteRepositoryImpl @Inject constructor(
    private val orbitParamsRemoteRepository: OrbitParamsRemoteRepositoryAlias
) :
    PayloadRemoteRepository<PayloadResponseImpl, PayloadImpl> {

    override fun responseToModels(payloadResponses: List<PayloadResponseImpl>): List<PayloadImpl> {
        val payloads = ArrayList<PayloadImpl>()

        payloadResponses.forEach {
            payloads.add(responseToModel(it))
        }

        return payloads
    }

    override fun responseToModel(payloadResponse: PayloadResponseImpl): PayloadImpl {
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
            orbit_params = payloadResponse.orbit_params?.let {
                orbitParamsRemoteRepository.responseToModel(it, payloadId) as OrbitParamsImpl
            }
        }
    }

    override fun generateId() = generateRandomId()

}