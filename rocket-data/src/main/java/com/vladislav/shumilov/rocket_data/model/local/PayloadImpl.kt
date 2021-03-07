package com.vladislav.shumilov.rocket_data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.vladislav.shumilov.rocket_data.model.local.PayloadImpl.Companion.TABLE_NAME
import com.vladislav.shumilov.rocket_domain.model.local.OrbitParams
import com.vladislav.shumilov.rocket_domain.model.local.Payload

@Entity(tableName = TABLE_NAME)
data class PayloadImpl(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override var id: String,

    @ColumnInfo(name = Columns.NORAD_ID)
    override var noradId: List<String>?,

    @ColumnInfo(name = Columns.REUSED)
    override var reused: Boolean,

    @ColumnInfo(name = Columns.CUSTOMERS)
    override var customers: List<String>?,

    @ColumnInfo(name = Columns.NATIONALITY)
    override var nationality: String?,

    @ColumnInfo(name = Columns.MANUFACTURER)
    override var manufacturer: String?,

    @ColumnInfo(name = Columns.TYPE)
    override var type: String?,

    @ColumnInfo(name = Columns.MASS_KG)
    override var massKg: Float?,

    @ColumnInfo(name = Columns.MASS_LBS)
    override var massLbs: Float?,

    @ColumnInfo(name = Columns.ORBIT)
    override var orbit: String?
) : Payload {

    @Ignore
    override var orbitParams: OrbitParams? = null

    companion object {
        const val TABLE_NAME = "payload"
    }

    class Columns {
        companion object {
            const val ID = "id"
            const val NORAD_ID = "norad_id"
            const val REUSED = "reused"
            const val CUSTOMERS = "customers"
            const val NATIONALITY = "nationality"
            const val MANUFACTURER = "manufacturer"
            const val TYPE = "type"
            const val MASS_KG = "mass_kg"
            const val MASS_LBS = "mass_lbs"
            const val ORBIT = "orbit"
        }
    }
}