package com.example.rocket_data.model.local

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.rocket_domain.model.local.FirstStageToCore

internal const val FIRST_STAGE_TO_CORE = "first_stage_to_core"

@Entity(
    tableName = FIRST_STAGE_TO_CORE,
    primaryKeys = ["first_stage_id", "core_id"],
    foreignKeys = [
        ForeignKey(
            entity = FirstStageImpl::class,
            parentColumns = ["id"],
            childColumns = ["first_stage_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CoreImpl::class,
            parentColumns = ["id"],
            childColumns = ["core_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FirstStageToCoreImpl(
    override val first_stage_id: String,
    override val core_id: String
) : FirstStageToCore