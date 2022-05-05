package com.vladislav.shumilov.rocket_data.repository

import com.vladislav.shumilov.rocket_data.database.OrbitParamsDao
import com.vladislav.shumilov.rocket_data.model.local.OrbitParamsImpl
import com.vladislav.shumilov.rocket_domain.model.local.OrbitParams
import com.vladislav.shumilov.rocket_domain.repository.OrbitParamsLocalRepository
import com.vladislav.shumilov.core_data.util.UNCHECKED_CAST
import javax.inject.Inject

class OrbitParamsLocalRepositoryImpl @Inject constructor(
    private val orbitParamsDao: OrbitParamsDao
) : OrbitParamsLocalRepository {

    @Suppress(UNCHECKED_CAST)
    override fun insertList(orbitParams: List<OrbitParams>) {
        orbitParamsDao.insertList(orbitParams as List<OrbitParamsImpl>)
    }

    @Suppress(UNCHECKED_CAST)
    override suspend fun getList() = orbitParamsDao.getList() as List<OrbitParams>
}