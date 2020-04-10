package com.example.payload_data.repository

import com.example.rocket_data.database.OrbitParamsDao
import com.example.rocket_data.database.PayloadDao
import com.example.rocket_data.model.local.OrbitParamsImpl
import com.example.rocket_data.model.local.PayloadImpl
import com.example.rocket_domain.repository.PayloadLocalRepository
import com.vladislav.shumilov.core_data.FragmentScope
import javax.inject.Inject

@FragmentScope
class PayloadLocalRepositoryImpl @Inject constructor(
    private val payloadDao: PayloadDao,
    private val orbitParamsDao: OrbitParamsDao
) :
    PayloadLocalRepository<PayloadImpl> {

    override fun insertList(payloads: List<PayloadImpl>) {
        payloadDao.insertList(payloads)

        insertOrbitParams(payloads)
    }

    override fun getList() = payloadDao.getList()

    private fun insertOrbitParams(launches: List<PayloadImpl>) {
        val orbitParams = ArrayList<OrbitParamsImpl>()

        launches.forEach {
            it.orbit_params?.let {
                orbitParams.add(it)
            }
        }

        if (orbitParams.isNotEmpty()) {
            orbitParamsDao.insertList(orbitParams)
        }
    }
}