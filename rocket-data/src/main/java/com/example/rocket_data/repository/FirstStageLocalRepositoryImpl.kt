package com.example.rocket_data.repository

import com.example.rocket_data.database.CoreDao
import com.example.rocket_data.database.FirstStageDao
import com.example.rocket_data.database.FirstStageToCoreDao
import com.example.rocket_data.model.local.CoreImpl
import com.example.rocket_data.model.local.FirstStageImpl
import com.example.rocket_data.model.local.FirstStageToCoreImpl
import com.example.rocket_domain.repository.FirstStageLocalRepository
import com.vladislav.shumilov.core_data.FragmentScope
import javax.inject.Inject

@FragmentScope
class FirstStageLocalRepositoryImpl @Inject constructor(
    private val firstStageDao: FirstStageDao,
    private val coreDao: CoreDao,
    private val firstStageToCoreDao: FirstStageToCoreDao
) :
    FirstStageLocalRepository<FirstStageImpl> {

    override fun insertList(firstStages: List<FirstStageImpl>) {
        firstStageDao.insertList(firstStages)

        insertCores(firstStages)
    }

    override fun getList() = firstStageDao.getList()

    private fun insertCores(firstStages: List<FirstStageImpl>) {
        val cores = ArrayList<CoreImpl>()
        val firstStageToCores = ArrayList<FirstStageToCoreImpl>()

        firstStages.forEach { firstStage ->
            firstStage.cores?.let {
                cores.addAll(it)

                it.forEach { core ->
                    firstStageToCores.add(FirstStageToCoreImpl(firstStage.id, core.id))
                }
            }
        }

        if (cores.isNotEmpty()) {
            coreDao.insertList(cores)
        }

        if (firstStageToCores.isNotEmpty()) {
            firstStageToCoreDao.insertList(firstStageToCores)
        }
    }
}