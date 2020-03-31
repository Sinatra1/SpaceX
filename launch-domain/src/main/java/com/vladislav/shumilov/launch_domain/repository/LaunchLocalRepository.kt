package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.local.LaunchAlias
import io.reactivex.Single

interface LaunchLocalRepository<T: LaunchAlias> {
    fun insertList(launches: List<T>)

    fun getList(): Single<List<T>>
}