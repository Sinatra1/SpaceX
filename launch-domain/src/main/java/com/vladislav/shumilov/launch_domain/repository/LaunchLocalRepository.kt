package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.local.Launch
import com.vladislav.shumilov.launch_domain.model.local.LaunchWithMissions
import io.reactivex.Single

interface LaunchLocalRepository {
    fun insertList(launches: List<Launch>)

    fun getList(limit: Int = Int.MAX_VALUE): Single<List<Launch>>

    fun getListWithMissions(
        offset: Int,
        limit: Int = Int.MAX_VALUE
    ): Single<List<LaunchWithMissions>>

    fun getListWithMissionsByList(launches: List<Launch>): List<LaunchWithMissions>
}