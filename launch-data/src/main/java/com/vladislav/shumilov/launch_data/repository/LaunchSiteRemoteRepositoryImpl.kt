package com.vladislav.shumilov.launch_data.repository

import com.vladislav.shumilov.launch_data.model.local.LaunchSiteImpl
import com.vladislav.shumilov.launch_domain.model.local.LaunchSite
import com.vladislav.shumilov.launch_domain.model.remote.LaunchSiteResponse
import com.vladislav.shumilov.launch_domain.repository.LaunchSiteRemoteRepository

class LaunchSiteRemoteRepositoryImpl : LaunchSiteRemoteRepository {

    override fun responseToModel(launchSiteResponse: LaunchSiteResponse): LaunchSite? =
        launchSiteResponse.site_id?.let { siteId ->
            LaunchSiteImpl(
                siteId,
                launchSiteResponse.site_name,
                launchSiteResponse.site_name_long
            )
        }

}