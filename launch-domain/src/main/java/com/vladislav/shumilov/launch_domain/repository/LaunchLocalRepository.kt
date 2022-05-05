package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.local.Launch
import com.vladislav.shumilov.launch_domain.model.local.LaunchForDetail
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import io.reactivex.Single

interface LaunchLocalRepository {
    fun insertList(launches: List<Launch>)

    fun getList(limit: Int = Int.MAX_VALUE): Single<List<Launch>>

    suspend fun getLaunchesForList(
        offset: Int,
        limit: Int = Int.MAX_VALUE
    ): List<LaunchForList>

    fun getLaunchForDetail(launchId: String): Single<LaunchForDetail>

    fun getListWithMissionsByList(launches: List<Launch>): List<LaunchForList>
}