package com.example.rocket_data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.rocket_data.model.local.SecondStageToPayloadImpl.Companion.TABLE_NAME
import com.example.rocket_domain.model.local.SecondStageToPayload

@Entity(
    tableName = TABLE_NAME,
    primaryKeys = [SecondStageToPayloadImpl.Columns.SECOND_STAGE_ID, SecondStageToPayloadImpl.Columns.PAYLOAD_ID],
    foreignKeys = [
        ForeignKey(
            entity = SecondStageImpl::class,
            parentColumns = [SecondStageImpl.Columns.ID],
            childColumns = [SecondStageToPayloadImpl.Columns.SECOND_STAGE_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PayloadImpl::class,
            parentColumns = [PayloadImpl.Columns.ID],
            childColumns = [SecondStageToPayloadImpl.Columns.PAYLOAD_ID],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class SecondStageToPayloadImpl(
    @ColumnInfo(name = Columns.SECOND_STAGE_ID)
    override var secondStageId: String,
    @ColumnInfo(name = Columns.PAYLOAD_ID)
    override var payloadId: String
) : SecondStageToPayload {

    companion object {
        const val TABLE_NAME = "second_stage_to_payload"
    }

    class Columns {
        companion object {
            const val SECOND_STAGE_ID = "second_stage_id"
            const val PAYLOAD_ID = "payload_id"
        }
    }
}