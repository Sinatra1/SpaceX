package com.example.rocket_data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.rocket_data.model.local.RocketImpl.Companion.TABLE_NAME
import com.example.rocket_domain.model.local.Fairings
import com.example.rocket_domain.model.local.FirstStage
import com.example.rocket_domain.model.local.Rocket
import com.example.rocket_domain.model.local.SecondStage

@Entity(tableName = TABLE_NAME)
data class RocketImpl(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override var id: String,

    @ColumnInfo(name = Columns.NAME)
    override var name: String?,

    @ColumnInfo(name = Columns.TYPE)
    override var type: String?
) : Rocket {
    @Ignore
    override var firstStage: FirstStage? = null

    @Ignore
    override var secondStage: SecondStage? = null

    @Ignore
    override var fairings: Fairings? = null

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