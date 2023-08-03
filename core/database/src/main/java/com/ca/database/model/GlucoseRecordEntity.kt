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
    date = dateTime.toLocalDate(),
    time = dateTime.toLocalTime(),
    measuringMark = measuringMark
)

fun GlucoseRecordEntity.asExternalModel() = GlucoseRecord(
    id = id,
    level = level,
    dateTime = LocalDateTime.of(date, time),
    note = note,
    measuringMark = measuringMark
)

