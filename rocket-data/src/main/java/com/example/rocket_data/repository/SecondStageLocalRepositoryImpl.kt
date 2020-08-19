package com.example.rocket_data.repository

import com.example.rocket_data.database.SecondStageDao
import com.example.rocket_data.database.SecondStageToPayloadDao
import com.example.rocket_data.model.local.SecondStageImpl
import com.example.rocket_data.model.local.SecondStageToPayloadImpl
import com.example.rocket_domain.model.local.Payload
import com.example.rocket_domain.model.local.SecondStage
import com.example.rocket_domain.repository.PayloadLocalRepository
import com.example.rocket_domain.repository.SecondStageLocalRepository
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.UNCHECKED_CAST
import io.reactivex.Single
import javax.inject.Inject

@FragmentScope
class SecondStageLocalRepositoryImpl @Inject constructor(
    private val secondStageDao: SecondStageDao,
    private val payloadLocalRepository: PayloadLocalRepository,
    private val secondStageToPayloadDao: SecondStageToPayloadDao
) :
    SecondStageLocalRepository {

    @Suppress(UNCHECKED_CAST)
    override fun insertList(secondStages: List<SecondStage>) {
        secondStageDao.insertList(secondStages as List<SecondStageImpl>)

        insertPayloads(secondStages)
    }

    @Suppress(UNCHECKED_CAST)
    override fun getList() = secondStageDao.getList() as Single<List<SecondStage>>

    private fun insertPayloads(secondStages: List<SecondStage>) {
        val payloads = mutableListOf<Payload>()
        val secondStageToPayloads = mutableListOf<SecondStageToPayloadImpl>()

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