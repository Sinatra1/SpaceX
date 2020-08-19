package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.local.LaunchSite
import com.vladislav.shumilov.launch_domain.model.remote.LaunchSiteResponse

interface LaunchSiteRemoteRepository {

    fun responseToModel(launchSiteResponse: LaunchSiteResponse): LaunchSite?
}