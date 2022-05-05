package com.vladislav.shumilov.launch_ui.common

import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_domain.model.local.LaunchForDetail
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import com.vladislav.shumilov.launch_domain.repository.LaunchLocalRepository
import com.vladislav.shumilov.launch_domain.repository.LaunchRemoteRepository
import com.vladislav.shumilov.launch_domain.ui.LaunchInteractor
import javax.inject.Inject

@FragmentScope
internal class LaunchInteractorImpl @Inject constructor(
    private val launchRemoteRepository: LaunchRemoteRepository,
    private val launchLocalRepository: LaunchLocalRepository
) : LaunchInteractor {

    override suspend fun getLaunchesForList(
        offset: Int,
        limit: Int
    ): List<LaunchForList> {
        val launchWithMissions = launchLocalRepository.getLaunchesForList(offset, limit)

        if (launchWithMissions.isNotEmpty()) {
            return launchWithMissions
        }

        val remoteLaunches = launchRemoteRepository.getList(offset, limit)
        val launches = launchRemoteRepository.responsesToModels(remoteLaunches)
        launchLocalRepository.insertList(launches)
        return launchLocalRepository.getListWithMissionsByList(launches)
    }

    override suspend fun getLaunchForDetail(launchId: String): LaunchForDetail =
        launchLocalRepository.getLaunchForDetail(launchId)

}