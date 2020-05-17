package com.vladislav.shumilov.launch_ui.common

import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_data.model.local.LaunchWithMissionsImpl
import com.vladislav.shumilov.launch_data.repository.LaunchLocalRepositoryImpl
import com.vladislav.shumilov.launch_data.repository.LaunchRemoteRepositoryImpl
import com.vladislav.shumilov.launch_domain.ui.LaunchInteractor
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@FragmentScope
class LaunchInteractorImpl @Inject constructor(
    private val launchRemoteRepository: LaunchRemoteRepositoryImpl,
    private val launchLocalRepository: LaunchLocalRepositoryImpl
) : LaunchInteractor<LaunchWithMissionsImpl> {

    override fun getListWithMissions(
        offset: Int,
        limit: Int
    ): Single<List<LaunchWithMissionsImpl>> =
        launchLocalRepository.getListWithMissions(offset, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .flatMap {
                if (it.isEmpty()) {
                    launchRemoteRepository.getList(offset, limit)
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .map {
                            launchRemoteRepository.responsesToModels(it)
                        }
                        .map {
                            launchLocalRepository.insertList(it)
                            it
                        }
                        .flatMap {
                            Single.just(launchLocalRepository.getListWithMissionsByList(it))
                        }
                } else {
                    Single.just(it)
                }
            }

}