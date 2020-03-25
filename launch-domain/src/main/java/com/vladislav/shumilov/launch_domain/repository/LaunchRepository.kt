package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.LaunchResponse
import io.reactivex.Single

interface LaunchRepository {
    fun getList(): Single<List<LaunchResponse>>
}