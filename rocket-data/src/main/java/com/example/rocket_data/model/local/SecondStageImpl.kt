package com.example.rocket_data.model.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.rocket_domain.model.local.SecondStage

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
class SecondStageImpl(
    @PrimaryKey
    override var id: String,
    override var rocket_id: String,
    override var block: Int?
    /*override var payloads: List<PayloadImpl>?*/
) :
    SecondStage<PayloadImpl>