package com.vladislav.shumilov.rocket_data.model.local

import androidx.room.Embedded
import androidx.room.Relation
import com.vladislav.shumilov.rocket_domain.model.local.RocketForDetail

data class RocketForDetailImpl(
    @Embedded
    override val rocket: RocketImpl,

    @Relation(
        parentColumn = RocketImpl.Columns.ID,
        entity = FirstStageImpl::class,
        entityColumn = FirstStageImpl.Columns.ROCKET_ID
    )
    override val firstStage: FirstStageForDetailImpl,

    @Relation(
        parentColumn = RocketImpl.Columns.ID,
        entity = SecondStageImpl::class,
        entityColumn = SecondStageImpl.Columns.ROCKET_ID
    )
    override val secondStage: SecondStageForDetailImpl?,

    @Relation(
        parentColumn = RocketImpl.Columns.ID,
        entity = FairingsImpl::class,
        entityColumn = FairingsImpl.Columns.ROCKET_ID
    )
    override val fairings: FairingsImpl?
) : RocketForDetail