package com.ca.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ca.domain.model.RecordGlucoseReminder
import com.ca.domain.model.ReminderIteration
import java.time.LocalTime

internal const val glucoseRemindersTableName = "glucose_reminder"

@Entity(tableName = glucoseRemindersTableName)
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
