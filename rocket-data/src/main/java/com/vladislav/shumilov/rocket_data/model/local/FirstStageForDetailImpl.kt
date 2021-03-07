package com.vladislav.shumilov.rocket_data.model.local

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.vladislav.shumilov.rocket_domain.model.local.FirstStageForDetail

data class FirstStageForDetailImpl(
    @Embedded
    override val firstStage: FirstStageImpl?,

    @Relation(
        parentColumn = FirstStageImpl.Columns.ID,
        entity = CoreImpl::class,
        entityColumn = CoreImpl.Columns.ID,
        associateBy = Junction(
            value = FirstStageToCoreImpl::class,
            parentColumn = FirstStageToCoreImpl.Columns.FIRST_STAGE_ID,
            entityColumn = FirstStageToCoreImpl.Columns.CORE_ID
        )
    )
    override val cores: List<CoreImpl>?
) : FirstStageForDetail