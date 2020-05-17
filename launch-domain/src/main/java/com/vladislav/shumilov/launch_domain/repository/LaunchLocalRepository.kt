package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.local.LaunchAlias
import com.vladislav.shumilov.launch_domain.model.local.LaunchWithMissionsAlias
import io.reactivex.Single

interface LaunchLocalRepository<T: LaunchAlias, LM: LaunchWithMissionsAlias> {
    fun insertList(launches: List<T>)

    fun getList(limit: Int = Int.MAX_VALUE): Single<List<T>>

    fun getListWithMissions(offset: Int, limit: Int = Int.MAX_VALUE): Single<List<LM>>

    fun getListWithMissionsByList(launches: List<T>): List<LM>
}