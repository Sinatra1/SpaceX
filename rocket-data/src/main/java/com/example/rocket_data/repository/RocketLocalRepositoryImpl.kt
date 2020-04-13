package com.example.rocket_data.repository

import com.example.rocket_data.database.FairingsDao
import com.example.rocket_data.database.RocketDao
import com.example.rocket_data.model.local.FairingsImpl
import com.example.rocket_data.model.local.FirstStageImpl
import com.example.rocket_data.model.local.RocketImpl
import com.example.rocket_data.model.local.SecondStageImpl
import com.example.rocket_domain.repository.FirstStageLocalRepositoryAlias
import com.example.rocket_domain.repository.RocketLocalRepository
import com.example.rocket_domain.repository.SecondStageLocalRepositoryAlias
import com.vladislav.shumilov.core_data.FragmentScope
import javax.inject.Inject

@FragmentScope
class RocketLocalRepositoryImpl @Inject constructor(
    private val rocketDao: RocketDao,
    private val firstStageLocalRepository: FirstStageLocalRepositoryAlias,
    private val secondStageLocalRepository: SecondStageLocalRepositoryAlias,
    private val fairingsDao: FairingsDao
) :
    RocketLocalRepository<RocketImpl> {

    override fun insertList(rockets: List<RocketImpl>) {
        rocketDao.insertList(rockets)

        insertFirstStages(rockets)
        insertSecondStages(rockets)
        insertFairings(rockets)
    }

    override fun getList() = rocketDao.getList()

    private fun insertFirstStages(rockets: List<RocketImpl>) {
        val firstStages = ArrayList<FirstStageImpl>()

        rockets.forEach {
            it.first_stage?.let {
                firstStages.add(it)
            }
        }

        if (firstStages.isNotEmpty()) {
            firstStageLocalRepository.insertList(firstStages)
        }
    }

    private fun insertSecondStages(rockets: List<RocketImpl>) {
        val secondStages = ArrayList<SecondStageImpl>()

        rockets.forEach {
            it.second_stage?.let {
                secondStages.add(it)
            }
        }

        if (secondStages.isNotEmpty()) {
            secondStageLocalRepository.insertList(secondStages)
        }
    }

    private fun insertFairings(rockets: List<RocketImpl>) {
        val fairings = ArrayList<FairingsImpl>()

        rockets.forEach {
            it.fairings?.let {
                fairings.add(it)
            }
        }

        if (fairings.isNotEmpty()) {
            fairingsDao.insertList(fairings)
        }
    }
}