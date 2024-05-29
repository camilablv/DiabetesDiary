package com.ca.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ca.model.RecordInsulinReminder
import com.ca.model.ReminderIteration
import java.time.LocalTime

internal const val insulinRemindersTableName = "insulin_reminder"

@Entity(tableName = insulinRemindersTableName)
data class RecordInsulinReminderEntity(
    val time: LocalTime,
    val iteration: ReminderIteration,
    @ColumnInfo(name = "insulin_id") val insulinId: String,
    val dose: Int,
    val enabled: Boolean,
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0
)

fun RecordInsulinReminderEntity.asExternalModel() = RecordInsulinReminder(
    id = id,
    time = time,
    iteration = iteration,
    insulinId = insulinId,
    dose = dose,
    enabled = enabled
)

fun RecordInsulinReminder.asEntity() = RecordInsulinReminderEntity(
    id = id,
    time = time,
    iteration = iteration,
    insulinId = insulinId,
    dose = dose,
    enabled = enabled
)

