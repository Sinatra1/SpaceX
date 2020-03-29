package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.local.LaunchAlias
import io.reactivex.Single

interface LaunchLocalRepository<T: LaunchAlias> {
    fun remoteListToLocalList(launchResponses: List<T>)

    fun remoteModelToLocalModel(launchResponse: T)

    fun getList(): Single<List<T>>
}