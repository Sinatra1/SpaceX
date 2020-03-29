package com.vladislav.shumilov.launch_data.model.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.vladislav.shumilov.launch_domain.model.local.LaunchFailureDetails

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = LaunchImpl::class,
            parentColumns = ["id"],
            childColumns = ["launch_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class LaunchFailureDetailsImpl(
    @PrimaryKey
    override var id: String,
    override var launch_id: String,
    override var time: Int?,
    override var altitude: String?,
    override var reason: String?
) :
    LaunchFailureDetails