package com.vladislav.shumilov.rocket_data.repository

import com.vladislav.shumilov.rocket_data.database.PayloadDao
import com.vladislav.shumilov.rocket_data.model.local.PayloadImpl
import com.vladislav.shumilov.rocket_domain.model.local.OrbitParams
import com.vladislav.shumilov.rocket_domain.model.local.Payload
import com.vladislav.shumilov.rocket_domain.repository.OrbitParamsLocalRepository
import com.vladislav.shumilov.rocket_domain.repository.PayloadLocalRepository
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.UNCHECKED_CAST
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
    override suspend fun getList() = payloadDao.getList() as List<Payload>

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