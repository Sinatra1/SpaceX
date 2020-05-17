package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.local.LaunchAlias
import com.vladislav.shumilov.launch_domain.model.remote.LaunchResponseAlias
import io.reactivex.Single

interface LaunchRemoteRepository<T: LaunchResponseAlias, M: LaunchAlias> {
    fun getList(offset: Int, limit: Int): Single<List<T>>

    fun responsesToModels(launchResponses: List<T>): List<M>

    fun responseToModel(launchResponse: T): M

    fun generateId(): String
}