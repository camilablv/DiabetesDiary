package com.ca.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ca.model.ReminderIteration
import java.time.LocalTime

@Entity(tableName = "glucose_reminder")
data class RecordGlucoseReminder(
    val time: LocalTime,
    val iteration: ReminderIteration,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
