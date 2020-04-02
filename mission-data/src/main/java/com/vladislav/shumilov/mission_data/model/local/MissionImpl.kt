package com.vladislav.shumilov.mission_data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladislav.shumilov.mission_domain.model.local.Mission

internal const val MISSION = "mission"

@Entity(tableName = MISSION)
data class MissionImpl(
    @PrimaryKey
    override var id: String,
    override var name: String?
) : Mission {
    override var wikipedia: String? = null
    override var website: String? = null
    override var twitter: String? = null
    override var description: String? = null
}