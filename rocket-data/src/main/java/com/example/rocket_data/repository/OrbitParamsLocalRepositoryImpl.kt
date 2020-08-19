package com.example.rocket_data.repository

import com.example.rocket_data.database.OrbitParamsDao
import com.example.rocket_data.model.local.OrbitParamsImpl
import com.example.rocket_domain.model.local.OrbitParams
import com.example.rocket_domain.repository.OrbitParamsLocalRepository
import com.vladislav.shumilov.core_data.util.UNCHECKED_CAST
import io.reactivex.Single
import javax.inject.Inject

class OrbitParamsLocalRepositoryImpl @Inject constructor(
    private val orbitParamsDao: OrbitParamsDao
) : OrbitParamsLocalRepository {

    @Suppress(UNCHECKED_CAST)
    override fun insertList(orbitParams: List<OrbitParams>) {
        orbitParamsDao.insertList(orbitParams as List<OrbitParamsImpl>)
    }

    @Suppress(UNCHECKED_CAST)
    override fun getList() = orbitParamsDao.getList() as Single<List<OrbitParams>>
}