package com.example.rocket_data.model.local

import androidx.room.*
import com.example.rocket_data.model.local.FirstStageImpl.Companion.TABLE_NAME
import com.example.rocket_domain.model.local.Core
import com.example.rocket_domain.model.local.FirstStage

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = RocketImpl::class,
            parentColumns = [RocketImpl.Columns.ID],
            childColumns = [FirstStageImpl.Columns.ROCKET_ID],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = [FirstStageImpl.Columns.ROCKET_ID])]
)
data class FirstStageImpl(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override var id: String,

    @ColumnInfo(name = Columns.ROCKET_ID)
    override var rocketId: String
) : FirstStage {

    @Ignore
    override var cores: List<Core>? = null

    companion object {
        const val TABLE_NAME = "firstStage"
    }

    class Columns {
        companion object {
            const val ID = "id"
            const val ROCKET_ID = "rocket_id"
        }
    }
}