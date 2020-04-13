package com.example.rocket_data.repository

import com.example.rocket_data.database.SecondStageDao
import com.example.rocket_data.database.SecondStageToPayloadDao
import com.example.rocket_data.model.local.PayloadImpl
import com.example.rocket_data.model.local.SecondStageImpl
import com.example.rocket_data.model.local.SecondStageToPayloadImpl
import com.example.rocket_domain.repository.PayloadLocalRepositoryAlias
import com.example.rocket_domain.repository.SecondStageLocalRepository
import com.vladislav.shumilov.core_data.FragmentScope
import javax.inject.Inject

@FragmentScope
class SecondStageLocalRepositoryImpl @Inject constructor(
    private val secondStageDao: SecondStageDao,
    private val payloadLocalRepository: PayloadLocalRepositoryAlias,
    private val secondStageToPayloadDao: SecondStageToPayloadDao
) :
    SecondStageLocalRepository<SecondStageImpl> {

    override fun insertList(secondStages: List<SecondStageImpl>) {
        secondStageDao.insertList(secondStages)

        insertPayloads(secondStages)
    }

    override fun getList() = secondStageDao.getList()

    private fun insertPayloads(secondStages: List<SecondStageImpl>) {
        val payloads = ArrayList<PayloadImpl>()
        val secondStageToPayloads = ArrayList<SecondStageToPayloadImpl>()

        secondStages.forEach { secondStage ->
            secondStage.payloads?.let {
                payloads.addAll(it)

                it.forEach { payload ->
                    secondStageToPayloads.add(SecondStageToPayloadImpl(secondStage.id, payload.id))
                }
            }
        }

        if (payloads.isNotEmpty()) {
            payloadLocalRepository.insertList(payloads)
        }

        if (secondStageToPayloads.isNotEmpty()) {
            secondStageToPayloadDao.insertList(secondStageToPayloads)
        }
    }
}