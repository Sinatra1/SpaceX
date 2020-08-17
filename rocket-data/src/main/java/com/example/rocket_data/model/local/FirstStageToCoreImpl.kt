package com.example.rocket_data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.rocket_data.model.local.FirstStageToCoreImpl.Companion.TABLE_NAME
import com.example.rocket_domain.model.local.FirstStageToCore

@Entity(
    tableName = TABLE_NAME,
    primaryKeys = [FirstStageToCoreImpl.Columns.FIRST_STAGE_ID, FirstStageToCoreImpl.Columns.CORE_ID],
    foreignKeys = [
        ForeignKey(
            entity = FirstStageImpl::class,
            parentColumns = [FirstStageImpl.Columns.ID],
            childColumns = [FirstStageToCoreImpl.Columns.FIRST_STAGE_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CoreImpl::class,
            parentColumns = [CoreImpl.Columns.ID],
            childColumns = [FirstStageToCoreImpl.Columns.CORE_ID],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FirstStageToCoreImpl(
    @ColumnInfo(name = Columns.FIRST_STAGE_ID)
    override val first_stage_id: String,
    @ColumnInfo(name = Columns.CORE_ID)
    override val core_id: String
) : FirstStageToCore {

    companion object {
        const val TABLE_NAME = "first_stage_to_core"
    }

    class Columns {
        companion object {
            const val FIRST_STAGE_ID = "first_stage_id"
            const val CORE_ID = "core_id"
        }
    }
}