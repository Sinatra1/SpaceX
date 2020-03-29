package com.example.rocket_data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rocket_domain.model.local.Rocket

@Entity
class RocketImpl(
    @PrimaryKey
    override var id: String,
    override var name: String?,
    override var type: String?,
    override var first_stage: FirstStageImpl?,
    override var second_stage: SecondStageImpl?,
    override var fairings: FairingsImpl?
) : Rocket<FirstStageImpl, SecondStageImpl, FairingsImpl>