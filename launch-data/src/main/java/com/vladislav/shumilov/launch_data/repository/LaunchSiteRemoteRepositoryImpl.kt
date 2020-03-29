package com.vladislav.shumilov.launch_data.repository

import com.vladislav.shumilov.launch_data.model.local.LaunchSiteImpl
import com.vladislav.shumilov.launch_data.model.remote.LaunchSiteResponseImpl
import com.vladislav.shumilov.launch_domain.repository.LaunchSiteRemoteRepository

class LaunchSiteRemoteRepositoryImpl:
    LaunchSiteRemoteRepository<LaunchSiteResponseImpl, LaunchSiteImpl> {

    override fun responseToModel(launchSiteResponse: LaunchSiteResponseImpl) =
        LaunchSiteImpl(
            launchSiteResponse.site_id,
            launchSiteResponse.site_name,
            launchSiteResponse.site_name_long)
}