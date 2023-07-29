package com.ca.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ca.model.Insulin
import com.ca.model.InsulinRecord
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Entity(tableName = "insulin_record")
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
    dateTime = LocalDateTime.of(date, time),
    units = units,
    note = note
)

fun InsulinRecord.asEntity() = InsulinRecordEntity(
    cursor = cursor,
    id = id,
    insulin = insulin.asEntity(),
    date = dateTime.toLocalDate(),
    time = dateTime.toLocalTime(),
    units = units,
    note = note
)


