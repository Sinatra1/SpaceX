package com.vladislav.shumilov.rocket_data.repository

import com.vladislav.shumilov.rocket_data.database.CoreDao
import com.vladislav.shumilov.rocket_data.database.FirstStageDao
import com.vladislav.shumilov.rocket_data.database.FirstStageToCoreDao
import com.vladislav.shumilov.rocket_data.model.local.CoreImpl
import com.vladislav.shumilov.rocket_data.model.local.FirstStageImpl
import com.vladislav.shumilov.rocket_data.model.local.FirstStageToCoreImpl
import com.vladislav.shumilov.rocket_domain.model.local.Core
import com.vladislav.shumilov.rocket_domain.model.local.FirstStage
import com.vladislav.shumilov.rocket_domain.repository.FirstStageLocalRepository
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.UNCHECKED_CAST
import io.reactivex.Single
import javax.inject.Inject

@FragmentScope
class FirstStageLocalRepositoryImpl @Inject constructor(
    private val firstStageDao: FirstStageDao,
    private val coreDao: CoreDao,
    private val firstStageToCoreDao: FirstStageToCoreDao
) :
    FirstStageLocalRepository {

    @Suppress(UNCHECKED_CAST)
    override fun insertList(firstStages: List<FirstStage>) {
        firstStageDao.insertList(firstStages as List<FirstStageImpl>)

        insertCores(firstStages)
    }

    @Suppress(UNCHECKED_CAST)
    override fun getList() = firstStageDao.getList() as Single<List<FirstStage>>

    private fun insertCores(firstStages: List<FirstStage>) {
        val cores = mutableListOf<Core>()
        val firstStageToCores = mutableListOf<FirstStageToCoreImpl>()

        firstStages.forEach { firstStage ->
            firstStage.cores?.let {
                cores.addAll(it)

                it.forEach { core ->
                    firstStageToCores.add(FirstStageToCoreImpl(firstStage.id, core.id))
                }
            }
        }

        if (cores.isNotEmpty()) {
            @Suppress(UNCHECKED_CAST)
            coreDao.insertList(cores as List<CoreImpl>)
        }

        if (firstStageToCores.isNotEmpty()) {
            firstStageToCoreDao.insertList(firstStageToCores)
        }
    }
}