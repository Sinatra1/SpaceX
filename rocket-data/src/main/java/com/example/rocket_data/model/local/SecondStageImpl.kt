package com.example.rocket_data.model.local

import androidx.room.*
import com.example.rocket_domain.model.local.SecondStage

internal const val SECOND_STAGE = "second_stage"

@Entity(
    tableName = SECOND_STAGE,
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
data class SecondStageImpl(
    @PrimaryKey
    override var id: String,
    override var rocket_id: String,
    override var block: Int?
) :
    SecondStage<PayloadImpl> {

    @Ignore
    override var payloads: List<PayloadImpl>? = null
}