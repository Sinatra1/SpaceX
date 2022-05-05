package com.vladislav.shumilov.launch_domain.ui

import com.vladislav.shumilov.launch_domain.model.local.LaunchForDetail
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList

interface LaunchInteractor {

    suspend fun getLaunchesForList(offset: Int, limit: Int): List<LaunchForList>

    suspend fun getLaunchForDetail(launchId: String): LaunchForDetail
}