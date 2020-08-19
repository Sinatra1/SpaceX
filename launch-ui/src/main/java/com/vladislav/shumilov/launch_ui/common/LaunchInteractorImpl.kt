package com.vladislav.shumilov.launch_ui.common

import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_domain.model.local.LaunchWithMissions
import com.vladislav.shumilov.launch_domain.repository.LaunchLocalRepository
import com.vladislav.shumilov.launch_domain.repository.LaunchRemoteRepository
import com.vladislav.shumilov.launch_domain.ui.LaunchInteractor
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@FragmentScope
class LaunchInteractorImpl @Inject constructor(
    private val launchRemoteRepository: LaunchRemoteRepository,
    private val launchLocalRepository: LaunchLocalRepository
) : LaunchInteractor {

    override fun getListWithMissions(
        offset: Int,
        limit: Int
    ): Single<List<LaunchWithMissions>> =
        launchLocalRepository.getListWithMissions(offset, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .flatMap { launchWithMissions ->
                if (launchWithMissions.isEmpty()) {
                    launchRemoteRepository.getList(offset, limit)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .map(launchRemoteRepository::responsesToModels)
                        .map {
                            launchLocalRepository.insertList(it)
                            it
                        }
                        .flatMap {
                            Single.just(launchLocalRepository.getListWithMissionsByList(it))
                        }
                } else {
                    Single.just(launchWithMissions)
                }
            }

}