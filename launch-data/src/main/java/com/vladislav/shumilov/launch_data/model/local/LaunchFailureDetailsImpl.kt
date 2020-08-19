package com.vladislav.shumilov.launch_data.model.local

import androidx.room.*
import com.vladislav.shumilov.launch_data.model.local.LaunchFailureDetailsImpl.Companion.TABLE_NAME
import com.vladislav.shumilov.launch_domain.model.local.LaunchFailureDetails

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = LaunchImpl::class,
            parentColumns = [LaunchFailureDetailsImpl.Columns.ID],
            childColumns = [LaunchFailureDetailsImpl.Columns.LAUNCH_ID],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = [LaunchFailureDetailsImpl.Columns.LAUNCH_ID])]
)
data class LaunchFailureDetailsImpl(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override var id: String,

    @ColumnInfo(name = Columns.LAUNCH_ID)
    override var launchId: String,

    @ColumnInfo(name = Columns.TIME)
    override var time: Int?,

    @ColumnInfo(name = Columns.ALTITUDE)
    override var altitude: String?,

    @ColumnInfo(name = Columns.REASON)
    override var reason: String?
) : LaunchFailureDetails {

    companion object {
        const val TABLE_NAME = "launchFailureDetails"
    }

    class Columns {
        companion object {
            const val ID = "id"
            const val LAUNCH_ID = "launchId"
            const val TIME = "time"
            const val ALTITUDE = "altitude"
            const val REASON = "reason"
        }
    }
}