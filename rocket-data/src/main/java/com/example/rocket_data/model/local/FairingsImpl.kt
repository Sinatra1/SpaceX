package com.example.rocket_data.model.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = RocketImpl::class,
            parentColumns = ["id"],
            childColumns = ["rocket_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class FairingsImpl(
    @PrimaryKey
    override var id: String,
    override var rocket_id: String,
    override var reused: Boolean,
    override var recovery_attempt: Boolean,
    override var recovered: Boolean,
    override var ship: String?
) : com.example.rocket_domain.model.local.Fairings