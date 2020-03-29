package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.local.Links
import com.vladislav.shumilov.launch_domain.model.remote.LinksResponse

interface LinksRemoteRepository<T: LinksResponse, M: Links> {

    fun responseToModel(linksResponse: T, launchId: String): M

    fun generateId(): String
}