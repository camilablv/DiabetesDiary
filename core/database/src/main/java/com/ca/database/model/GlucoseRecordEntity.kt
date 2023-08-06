package com.ca.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ca.model.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Entity(tableName = "glucose_record")
data class GlucoseRecordEntity(
    @PrimaryKey val id: String,
    val level: Double,
    val note: String?,
    val date: LocalDate,
    val time: LocalTime,
    val measuringMark: MeasuringMark
)

fun GlucoseRecord.asEntity() = GlucoseRecordEntity(
    id = id,
    level = level,
    note = note,
    date = date,
    time = time,
    measuringMark = measuringMark
)

fun GlucoseRecordEntity.asExternalModel() = GlucoseRecord(
    id = id,
    level = level,
    date = date,
    time = time,
    note = note,
    measuringMark = measuringMark
)

