package com.example.rocket_data.model.local

import androidx.room.*
import com.example.rocket_data.model.local.SecondStageImpl.Companion.TABLE_NAME
import com.example.rocket_domain.model.local.SecondStage


@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = RocketImpl::class,
            parentColumns = [RocketImpl.Columns.ID],
            childColumns = [SecondStageImpl.Columns.ROCKET_ID],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = [SecondStageImpl.Columns.ROCKET_ID])]
)
data class SecondStageImpl(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override var id: String,
    @ColumnInfo(name = Columns.ROCKET_ID)
    override var rocketId: String,
    @ColumnInfo(name = Columns.BLOCK)
    override var block: Int?
) :
    SecondStage<PayloadImpl> {

    @Ignore
    override var payloads: List<PayloadImpl>? = null

    companion object {
        const val TABLE_NAME = "second_stage"
    }

    class Columns {
        companion object {
            const val ID = "id"
            const val ROCKET_ID = "rocket_id"
            const val BLOCK = "block"
        }
    }
}