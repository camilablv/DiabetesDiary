package com.ca.model

import androidx.room.*
import java.time.LocalTime

@Entity(tableName = "insulin_reminder")
data class RecordInsulinReminder(
    val time: LocalTime,
    val iteration: ReminderIteration,
    @ColumnInfo(name = "insulin_id") val insulinId: String,
    val dose: Int,
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0
)