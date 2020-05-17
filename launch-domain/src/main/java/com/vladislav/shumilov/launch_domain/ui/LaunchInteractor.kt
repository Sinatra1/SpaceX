package com.vladislav.shumilov.launch_domain.ui

import com.vladislav.shumilov.launch_domain.model.local.LaunchWithMissionsAlias
import io.reactivex.Single

interface LaunchInteractor<LM: LaunchWithMissionsAlias> {

    fun getListWithMissions(offset: Int, limit: Int): Single<List<LM>>
}