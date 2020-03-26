package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.LaunchResponseAlias
import io.reactivex.Single

interface LaunchRepository<T: LaunchResponseAlias> {
    fun getList(): Single<List<T>>
}