package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.local.Launch
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import io.reactivex.Single

interface LaunchLocalRepository {
    fun insertList(launches: List<Launch>)

    fun getList(limit: Int = Int.MAX_VALUE): Single<List<Launch>>

    fun getLaunchesForList(
        offset: Int,
        limit: Int = Int.MAX_VALUE
    ): Single<List<LaunchForList>>

    fun getListWithMissionsByList(launches: List<Launch>): List<LaunchForList>
}