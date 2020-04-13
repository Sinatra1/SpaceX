package com.example.rocket_data.model.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.rocket_domain.model.local.Fairings

internal const val FAIRINGS = "fairings"

@Entity(
    tableName = FAIRINGS,
    foreignKeys = [
        ForeignKey(
            entity = RocketImpl::class,
            parentColumns = ["id"],
            childColumns = ["rocket_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["rocket_id"])]
)
data class FairingsImpl(
    @PrimaryKey
    override var id: String,
    override var rocket_id: String,
    override var reused: Boolean,
    override var recovery_attempt: Boolean,
    override var recovered: Boolean,
    override var ship: String?
) : Fairings