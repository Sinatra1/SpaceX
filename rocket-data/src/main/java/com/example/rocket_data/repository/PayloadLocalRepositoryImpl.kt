package com.example.payload_data.repository

import com.example.rocket_data.database.PayloadDao
import com.example.rocket_data.model.local.PayloadImpl
import com.example.rocket_domain.model.local.OrbitParams
import com.example.rocket_domain.model.local.Payload
import com.example.rocket_domain.repository.OrbitParamsLocalRepository
import com.example.rocket_domain.repository.PayloadLocalRepository
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.UNCHECKED_CAST
import io.reactivex.Single
import javax.inject.Inject

@FragmentScope
class PayloadLocalRepositoryImpl @Inject constructor(
    private val payloadDao: PayloadDao,
    private val orbitParamsLocalRepository: OrbitParamsLocalRepository
) :
    PayloadLocalRepository {

    @Suppress(UNCHECKED_CAST)
    override fun insertList(payloads: List<Payload>) {
        payloadDao.insertList(payloads as List<PayloadImpl>)

        insertOrbitParams(payloads)
    }

    @Suppress(UNCHECKED_CAST)
    override fun getList() = payloadDao.getList() as Single<List<Payload>>

    private fun insertOrbitParams(launches: List<Payload>) {
        val orbitParams = mutableListOf<OrbitParams>()

        launches.forEach {
            it.orbitParams?.let(orbitParams::add)
        }

        if (orbitParams.isNotEmpty()) {
            orbitParamsLocalRepository.insertList(orbitParams)
        }
    }
}