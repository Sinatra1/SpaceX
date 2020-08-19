package com.example.rocket_data.model.local

import androidx.room.*
import com.example.rocket_data.model.local.FairingsImpl.Companion.TABLE_NAME
import com.example.rocket_domain.model.local.Fairings

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = RocketImpl::class,
            parentColumns = [RocketImpl.Columns.ID],
            childColumns = [FairingsImpl.Columns.ROCKET_ID],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = [FairingsImpl.Columns.ROCKET_ID])]
)
data class FairingsImpl(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override var id: String,

    @ColumnInfo(name = Columns.ROCKET_ID)
    override var rocketId: String,

    @ColumnInfo(name = Columns.REUSED)
    override var reused: Boolean,

    @ColumnInfo(name = Columns.RECOVERY_ATTEMPT)
    override var recoveryAttempt: Boolean,

    @ColumnInfo(name = Columns.RECOVERED)
    override var recovered: Boolean,

    @ColumnInfo(name = Columns.SHIP)
    override var ship: String?
) : Fairings {

    companion object {
        const val TABLE_NAME = "fairings"
    }

    class Columns {
        companion object {
            const val ID = "id"
            const val ROCKET_ID = "rocketId"
            const val REUSED = "reused"
            const val RECOVERY_ATTEMPT = "recovery_attempt"
            const val RECOVERED = "recovered"
            const val SHIP = "ship"
        }
    }
}