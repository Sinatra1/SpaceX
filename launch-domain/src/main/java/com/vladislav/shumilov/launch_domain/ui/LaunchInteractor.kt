package com.vladislav.shumilov.launch_domain.ui

import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import io.reactivex.Single

interface LaunchInteractor {

    fun getLaunchesForList(offset: Int, limit: Int): Single<List<LaunchForList>>
}