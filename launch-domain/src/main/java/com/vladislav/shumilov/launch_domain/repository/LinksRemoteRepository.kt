package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.local.Links
import com.vladislav.shumilov.launch_domain.model.remote.LinksResponse

interface LinksRemoteRepository {

    fun responseToModel(linksResponse: LinksResponse, launchId: String): Links

    fun generateId(): String
}