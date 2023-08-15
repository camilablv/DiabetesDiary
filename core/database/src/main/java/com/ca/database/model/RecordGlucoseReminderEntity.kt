package com.ca.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ca.model.RecordGlucoseReminder
import com.ca.model.ReminderIteration
import java.time.LocalTime

internal const val glucoseReminderTableName = "glucose_reminder"

@Entity(tableName = glucoseReminderTableName)
data class RecordGlucoseReminderEntity(
    val time: LocalTime,
    val iteration: ReminderIteration,
    val enabled: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)

fun RecordGlucoseReminderEntity.asExternalModel() = RecordGlucoseReminder(
    id = id,
    time = time,
    iteration = iteration,
    enabled = enabled
)

fun RecordGlucoseReminder.asEntity() = RecordGlucoseReminderEntity(
    id = id,
    time = time,
    iteration = iteration,
    enabled = enabled
)
