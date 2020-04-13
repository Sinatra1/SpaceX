package com.example.rocket_data.model.local

import androidx.room.*
import com.example.rocket_domain.model.local.FirstStage

internal const val FIRST_STAGE = "first_stage"

@Entity(
    tableName = FIRST_STAGE,
    foreignKeys = [
        ForeignKey(
            entity = RocketImpl::class,
            parentColumns = ["id"],
            childColumns = ["rocket_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["rocket_id"])]
)
data class FirstStageImpl(
    @PrimaryKey
    override var id: String,
    override var rocket_id: String
) : FirstStage<CoreImpl> {

    @Ignore
    override var cores: List<CoreImpl>? = null
}