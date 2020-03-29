package com.vladislav.shumilov.launch_data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladislav.shumilov.launch_domain.model.local.Mission

@Entity
class MissionImpl(
    @PrimaryKey
    override var id: String,
    override var name: String,
    override var wikipedia: String?,
    override var website: String?,
    override var twitter: String?,
    override var description: String?
) : Mission