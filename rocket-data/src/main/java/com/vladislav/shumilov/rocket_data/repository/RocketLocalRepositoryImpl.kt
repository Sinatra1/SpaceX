package com.vladislav.shumilov.rocket_data.repository

import com.vladislav.shumilov.rocket_data.database.FairingsDao
import com.vladislav.shumilov.rocket_data.database.RocketDao
import com.vladislav.shumilov.rocket_data.model.local.FairingsImpl
import com.vladislav.shumilov.rocket_data.model.local.RocketImpl
import com.vladislav.shumilov.rocket_domain.model.local.Fairings
import com.vladislav.shumilov.rocket_domain.model.local.FirstStage
import com.vladislav.shumilov.rocket_domain.model.local.Rocket
import com.vladislav.shumilov.rocket_domain.model.local.SecondStage
import com.vladislav.shumilov.rocket_domain.repository.FirstStageLocalRepository
import com.vladislav.shumilov.rocket_domain.repository.RocketLocalRepository
import com.vladislav.shumilov.rocket_domain.repository.SecondStageLocalRepository
import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.core_data.util.UNCHECKED_CAST
import io.reactivex.Single
import javax.inject.Inject

@FragmentScope
class RocketLocalRepositoryImpl @Inject constructor(
    private val rocketDao: RocketDao,
    private val firstStageLocalRepository: FirstStageLocalRepository,
    private val secondStageLocalRepository: SecondStageLocalRepository,
    private val fairingsDao: FairingsDao
) :
    RocketLocalRepository {

    @Suppress(UNCHECKED_CAST)
    override fun insertList(rockets: List<Rocket>) {
        rocketDao.insertList(rockets as List<RocketImpl>)

        insertFirstStages(rockets)
        insertSecondStages(rockets)
        insertFairings(rockets)
    }

    @Suppress(UNCHECKED_CAST)
    override fun getList() = rocketDao.getList() as Single<List<Rocket>>

    private fun insertFirstStages(rockets: List<Rocket>) {
        val firstStages = mutableListOf<FirstStage>()

        rockets.forEach {
            it.firstStage?.let(firstStages::add)
        }

        if (firstStages.isNotEmpty()) {
            firstStageLocalRepository.insertList(firstStages)
        }
    }

    private fun insertSecondStages(rockets: List<Rocket>) {
        val secondStages = mutableListOf<SecondStage>()

        rockets.forEach {
            it.secondStage?.let(secondStages::add)
        }

        if (secondStages.isNotEmpty()) {
            secondStageLocalRepository.insertList(secondStages)
        }
    }

    private fun insertFairings(rockets: List<Rocket>) {
        val fairings = mutableListOf<Fairings>()

        rockets.forEach {
            it.fairings?.let(fairings::add)
        }

        if (fairings.isNotEmpty()) {
            @Suppress(UNCHECKED_CAST)
            fairingsDao.insertList(fairings as List<FairingsImpl>)
        }
    }
}