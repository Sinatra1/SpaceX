package com.example.rocket_data.model.local

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.rocket_domain.model.local.Rocket

internal const val ROCKET = "rocket"

@Entity(tableName = ROCKET)
data class RocketImpl(
    @PrimaryKey
    override var id: String,
    override var name: String?,
    override var type: String?
) : Rocket<FirstStageImpl, SecondStageImpl, FairingsImpl> {
    @Ignore
    override var first_stage: FirstStageImpl? = null
    @Ignore
    override var second_stage: SecondStageImpl? = null
    @Ignore
    override var fairings: FairingsImpl? = null
}