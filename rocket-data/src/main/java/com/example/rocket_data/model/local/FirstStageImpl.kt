package com.example.rocket_data.model.local

import androidx.room.*
import com.example.rocket_data.model.local.FirstStageImpl.Companion.TABLE_NAME
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
    override var rocket_id: String
) : FirstStage<CoreImpl> {

    @Ignore
    override var cores: List<CoreImpl>? = null

    companion object {
        const val TABLE_NAME = "first_stage"
    }

    class Columns {
        companion object {
            const val ID = "id"
            const val ROCKET_ID = "rocketId"
        }
    }
}