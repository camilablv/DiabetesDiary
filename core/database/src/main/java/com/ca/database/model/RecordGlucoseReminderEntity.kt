package com.ca.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ca.model.RecordGlucoseReminder
import com.ca.model.ReminderIteration
import java.time.LocalTime

@Entity(tableName = "glucose_reminder")
data class RecordGlucoseReminderEntity(
    val time: LocalTime,
    val iteration: ReminderIteration,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)

fun RecordGlucoseReminderEntity.asExternalModel() = RecordGlucoseReminder(
    id = id,
    time = time,
    iteration = iteration
)

fun RecordGlucoseReminder.asEntity() = RecordGlucoseReminderEntity(
    id = id,
    time = time,
    iteration = iteration
)
