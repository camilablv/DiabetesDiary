package com.ca.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ca.domain.model.Insulin
import com.ca.domain.model.InsulinRecord
import java.time.LocalDate
import java.time.LocalTime

internal const val insulinRecordsTableName = "insulin_record"

@Entity(tableName = insulinRecordsTableName)
data class InsulinRecordEntity(
    val cursor: String,
    @PrimaryKey val id: String,
    @Embedded(prefix = "insulin") val insulin: InsulinEntity,
    val date: LocalDate,
    val time: LocalTime,
    val units: Double,
    val note: String?
)

data class InsulinEntity(
    val id: String,
    val name: String,
    val color: String,
    @ColumnInfo(name = "default_dose") val defaultDose: Int
)

fun InsulinEntity.asExternalModel() = Insulin(
    id = id,
    name = name,
    color = color,
    defaultDose = defaultDose
)

fun Insulin.asEntity() = InsulinEntity(
    id = id,
    name = name,
    color = color,
    defaultDose = defaultDose
)

fun InsulinRecordEntity.asExternalModel() = InsulinRecord(
    cursor = cursor,
    id = id,
    insulin = insulin.asExternalModel(),
    date = date,
    time = time,
    units = units,
    note = note
)

fun InsulinRecord.asEntity() = InsulinRecordEntity(
    cursor = cursor,
    id = id,
    insulin = insulin.asEntity(),
    date = date,
    time = time,
    units = units,
    note = note
)


