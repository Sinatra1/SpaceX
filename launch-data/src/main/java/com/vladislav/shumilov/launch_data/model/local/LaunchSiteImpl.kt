package com.vladislav.shumilov.launch_data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladislav.shumilov.launch_domain.model.local.LaunchSite

@Entity
class LaunchSiteImpl(
    @PrimaryKey
    override var id: String?,
    override var name: String?,
    override var name_long: String?
) : LaunchSite