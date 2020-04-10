package com.example.rocket_data.repository

import com.example.rocket_data.database.RocketDao
import com.example.rocket_data.database.SecondStageDao
import com.example.rocket_data.model.local.FirstStageImpl
import com.example.rocket_data.model.local.RocketImpl
import com.example.rocket_data.model.local.SecondStageImpl
import com.example.rocket_domain.repository.RocketLocalRepository
import com.vladislav.shumilov.core_data.FragmentScope
import javax.inject.Inject

@FragmentScope
class RocketLocalRepositoryImpl @Inject constructor(
    private val rocketDao: RocketDao,
    private val firstStageLocalRepository: FirstStageLocalRepositoryImpl,
    private val secondStageDao: SecondStageDao
) :
    RocketLocalRepository<RocketImpl> {

    override fun insertList(launches: List<RocketImpl>) {
        rocketDao.insertList(launches)

        insertFirstStages(launches)
        insertSecondStages(launches)
    }

    override fun getList() = rocketDao.getList()

    private fun insertFirstStages(launches: List<RocketImpl>) {
        val firstStages = ArrayList<FirstStageImpl>()

        launches.forEach {
            it.first_stage?.let {
                firstStages.add(it)
            }
        }

        if (firstStages.isNotEmpty()) {
            firstStageLocalRepository.insertList(firstStages)
        }
    }

    private fun insertSecondStages(launches: List<RocketImpl>) {
        val secondStages = ArrayList<SecondStageImpl>()

        launches.forEach {
            it.second_stage?.let {
                secondStages.add(it)
            }
        }

        if (secondStages.isNotEmpty()) {
            secondStageDao.insertList(secondStages)
        }
    }
}