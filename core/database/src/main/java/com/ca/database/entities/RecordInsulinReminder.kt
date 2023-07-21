package com.ca.database.entities

import androidx.room.*
import com.ca.database.converters.LocalTimeConverter
import com.ca.model.Insulin
import com.ca.model.ReminderIteration
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