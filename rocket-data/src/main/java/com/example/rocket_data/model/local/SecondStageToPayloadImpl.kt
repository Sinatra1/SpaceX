package com.example.rocket_data.model.local

import com.example.rocket_domain.model.local.SecondStageToPayload

import androidx.room.Entity
import androidx.room.ForeignKey

internal const val SECOND_STAGE_TO_PAYLOAD = "second_stage_to_payload"

@Entity(
    tableName = SECOND_STAGE_TO_PAYLOAD,
    primaryKeys = ["second_stage_id", "payload_id"],
    foreignKeys = [
        ForeignKey(
            entity = FirstStageImpl::class,
            parentColumns = ["id"],
            childColumns = ["second_stage_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CoreImpl::class,
            parentColumns = ["id"],
            childColumns = ["payload_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class SecondStageToPayloadImpl(
    override var second_stage_id: String,
    override var payload_id: String
) : SecondStageToPayload