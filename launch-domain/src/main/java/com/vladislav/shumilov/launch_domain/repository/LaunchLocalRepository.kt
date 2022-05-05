package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.local.Launch
import com.vladislav.shumilov.launch_domain.model.local.LaunchForDetail
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList

interface LaunchLocalRepository {
    fun insertList(launches: List<Launch>)

    suspend fun getList(limit: Int = Int.MAX_VALUE): List<Launch>

    suspend fun getLaunchesForList(
        offset: Int,
        limit: Int = Int.MAX_VALUE
    ): List<LaunchForList>

    suspend fun getLaunchForDetail(launchId: String): LaunchForDetail

    fun getListWithMissionsByList(launches: List<Launch>): List<LaunchForList>
}