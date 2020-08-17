package com.vladislav.shumilov.launch_domain.repository

import com.vladislav.shumilov.launch_domain.model.local.LaunchSite
import com.vladislav.shumilov.launch_domain.model.remote.LaunchSiteResponse

typealias LaunchSiteRemoteRepositoryAlias = LaunchSiteRemoteRepository<LaunchSiteResponse, LaunchSite>

interface LaunchSiteRemoteRepository<T: LaunchSiteResponse, M: LaunchSite> {

    fun responseToModel(launchSiteResponse: T): M?
}