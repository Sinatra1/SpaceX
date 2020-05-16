package com.vladislav.shumilov.launch_ui.common

import com.vladislav.shumilov.core_data.FragmentScope
import com.vladislav.shumilov.launch_data.model.local.LaunchImpl
import com.vladislav.shumilov.launch_data.repository.LaunchLocalRepositoryImpl
import com.vladislav.shumilov.launch_data.repository.LaunchRemoteRepositoryImpl
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

const val ITEMS_LIMIT = 10

@FragmentScope
class LaunchInteractor @Inject constructor(
    private val launchRemoteRepository: LaunchRemoteRepositoryImpl,
    private val launchLocalRepository: LaunchLocalRepositoryImpl
) {

    fun getList(): Single<List<LaunchImpl>> =
        launchLocalRepository.getList(ITEMS_LIMIT)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .flatMap {
                if (it.isEmpty()) {
                    launchRemoteRepository.getList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io())
                        .map {
                            launchRemoteRepository.responsesToModels(it)
                        }
                        .map {
                            launchLocalRepository.insertList(it)
                            it
                        }
                } else {
                    Single.just(it)
                }
            }

}