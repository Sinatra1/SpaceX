package com.example.rocket_data.model.local

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.rocket_domain.model.local.SecondStageForDetail

data class SecondStageForDetailImpl(
    @Embedded
    override val secondStage: SecondStageImpl,

    @Relation(
        parentColumn = SecondStageImpl.Columns.ID,
        entity = PayloadImpl::class,
        entityColumn = PayloadImpl.Columns.ID,
        associateBy = Junction(
            value = SecondStageToPayloadImpl::class,
            parentColumn = SecondStageToPayloadImpl.Columns.SECOND_STAGE_ID,
            entityColumn = SecondStageToPayloadImpl.Columns.PAYLOAD_ID
        )
    )
    override val payloads: List<PayloadForDetailImpl>?
) : SecondStageForDetail