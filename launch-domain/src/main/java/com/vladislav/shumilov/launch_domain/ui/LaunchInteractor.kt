package com.vladislav.shumilov.launch_domain.ui

import com.vladislav.shumilov.launch_domain.model.local.LaunchWithMissions
import io.reactivex.Single

interface LaunchInteractor {

    fun getListWithMissions(offset: Int, limit: Int): Single<List<LaunchWithMissions>>
}