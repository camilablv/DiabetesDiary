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
    val units: GlucoseUnits,
    val level: Double,
    val note: String,
    val date: LocalDate,
    val time: LocalTime,
    val measuringMark: String
)

fun GlucoseRecordEntity.asExternalModel() = GlucoseRecord(
    id = id,
    level = level,
    dateTime = LocalDateTime.of(date, time),
    note = note,
    measuringMark = measuringMark
)

