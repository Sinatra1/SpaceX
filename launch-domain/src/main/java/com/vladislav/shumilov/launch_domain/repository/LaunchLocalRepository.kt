package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.local.LaunchAlias
import io.reactivex.Single

interface LaunchLocalRepository<T: LaunchAlias> {
    fun insertList(launches: List<T>)

    fun getList(limit: Int = Int.MAX_VALUE): Single<List<T>>
}