package com.example.rocket_data.model.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.rocket_domain.model.local.FirstStage

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
class FirstStageImpl(
    @PrimaryKey
    override var id: String,
    override var rocket_id: String
    /*override var cores: List<CoreImpl>?*/
) : FirstStage<CoreImpl>