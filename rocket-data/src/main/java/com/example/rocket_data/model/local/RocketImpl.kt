package com.example.rocket_data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.rocket_data.model.local.RocketImpl.Companion.TABLE_NAME
import com.example.rocket_domain.model.local.Rocket

@Entity(tableName = TABLE_NAME)
data class RocketImpl(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override var id: String,
    @ColumnInfo(name = Columns.NAME)
    override var name: String?,
    @ColumnInfo(name = Columns.TYPE)
    override var type: String?
) : Rocket<FirstStageImpl, SecondStageImpl, FairingsImpl> {
    @Ignore
    override var first_stage: FirstStageImpl? = null
    @Ignore
    override var second_stage: SecondStageImpl? = null
    @Ignore
    override var fairings: FairingsImpl? = null

    companion object {
        const val TABLE_NAME = "rocket"
    }

    class Columns {
        companion object {
            const val ID = "id"
            const val NAME = "name"
            const val TYPE = "type"
        }
    }
}