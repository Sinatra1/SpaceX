package com.vladislav.shumilov.rocket_data.model.local

import androidx.room.*
import com.vladislav.shumilov.rocket_data.model.local.OrbitParamsImpl.Companion.TABLE_NAME
import com.vladislav.shumilov.rocket_domain.model.local.OrbitParams

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = PayloadImpl::class,
            parentColumns = [PayloadImpl.Columns.ID],
            childColumns = [OrbitParamsImpl.Columns.PAYLOAD_ID],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = [OrbitParamsImpl.Columns.PAYLOAD_ID])]
)
data class OrbitParamsImpl(
    @PrimaryKey
    @ColumnInfo(name = Columns.ID)
    override var id: String,

    @ColumnInfo(name = Columns.PAYLOAD_ID)
    override var payloadId: String,

    @ColumnInfo(name = Columns.REFERENCE_SYSTEM)
    override var referenceSystem: String?,

    @ColumnInfo(name = Columns.REGIME)
    override var regime: String?,

    @ColumnInfo(name = Columns.LONGITUDE)
    override var longitude: Float?,

    @ColumnInfo(name = Columns.SEMI_MAJOR_AXIS_KM)
    override var semiMajorAxisKm: Float?,

    @ColumnInfo(name = Columns.ECCENTRICITY)
    override var eccentricity: Float?,

    @ColumnInfo(name = Columns.PERIAPSIS_KM)
    override var periapsisKm: Float?,

    @ColumnInfo(name = Columns.APOAPSIS_KM)
    override var apoapsisKm: Float?,

    @ColumnInfo(name = Columns.INCLINATION_DEG)
    override var inclinationDeg: Float?,

    @ColumnInfo(name = Columns.PERIOD_MIN)
    override var periodMin: Float?,

    @ColumnInfo(name = Columns.LIFESPAN_YEARS)
    override var lifespanYears: Float?,

    @ColumnInfo(name = Columns.EPOCH)
    override var epoch: String?,

    @ColumnInfo(name = Columns.MEAN_MOTION)
    override var meanMotion: Float?,

    @ColumnInfo(name = Columns.RAAN)
    override var raan: Float?,

    @ColumnInfo(name = Columns.ARG_OF_PERICENTER)
    override var argOfPericenter: String?,

    @ColumnInfo(name = Columns.MEAN_ANOMALY)
    override var meanAnomaly: String?
) : OrbitParams {

    companion object {
        const val TABLE_NAME = "orbitParams"
    }

    class Columns {
        companion object {
            const val ID = "id"
            const val PAYLOAD_ID = "payloadId"
            const val REFERENCE_SYSTEM = "reference_system"
            const val REGIME = "regime"
            const val LONGITUDE = "longitude"
            const val SEMI_MAJOR_AXIS_KM = "semi_major_axis_km"
            const val ECCENTRICITY = "eccentricity"
            const val PERIAPSIS_KM = "periapsis_km"
            const val APOAPSIS_KM = "apoapsis_km"
            const val INCLINATION_DEG = "inclination_deg"
            const val PERIOD_MIN = "period_min"
            const val LIFESPAN_YEARS = "lifespan_years"
            const val EPOCH = "epoch"
            const val MEAN_MOTION = "mean_motion"
            const val RAAN = "raan"
            const val ARG_OF_PERICENTER = "arg_of_pericenter"
            const val MEAN_ANOMALY = "mean_anomaly"
        }
    }
}