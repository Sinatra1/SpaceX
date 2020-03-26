package com.vladislav.shumilov.launch_ui.common

import com.vladislav.shumilov.launch_data.LaunchScope
import com.vladislav.shumilov.launch_data.model.LaunchResponseImpl
import com.vladislav.shumilov.launch_data.repository.LaunchRemoteRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@LaunchScope
class LaunchInteractor @Inject constructor(
    private val launchRemoteRepository: LaunchRemoteRepository
) {

    fun getList(): Single<List<LaunchResponseImpl>> =
        launchRemoteRepository.getList()
            .subscribeOn(Schedulers.io())
            .map {
                it.forEach {
                    it
                }
            it
        }
}