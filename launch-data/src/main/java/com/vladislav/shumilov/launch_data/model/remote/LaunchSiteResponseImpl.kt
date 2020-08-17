package com.vladislav.shumilov.launch_data.model.remote

import com.vladislav.shumilov.launch_domain.model.remote.LaunchSiteResponse

class LaunchSiteResponseImpl(
    override val site_id: String?,
    override val site_name: String?,
    override val site_name_long: String?
) : LaunchSiteResponse