package com.vladislav.shumilov.rocket_data.model.local

import androidx.room.Embedded
import androidx.room.Relation
import com.vladislav.shumilov.rocket_domain.model.local.PayloadForDetail

data class PayloadForDetailImpl(
    @Embedded
    override val payload: PayloadImpl,

    @Relation(
        parentColumn = PayloadImpl.Columns.ID,
        entity = OrbitParamsImpl::class,
        entityColumn = OrbitParamsImpl.Columns.PAYLOAD_ID
    )
    override val orbitParams: OrbitParamsImpl?
) : PayloadForDetail