package com.vladislav.shumilov.launch_domain.ui

import com.vladislav.shumilov.launch_domain.model.local.LaunchForDetail
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import io.reactivex.Single

interface LaunchInteractor {

    suspend fun getLaunchesForList(offset: Int, limit: Int): List<LaunchForList>

    fun getLaunchForDetail(launchId: String): Single<LaunchForDetail>
}