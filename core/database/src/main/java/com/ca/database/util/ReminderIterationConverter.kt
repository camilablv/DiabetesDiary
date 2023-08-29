package com.ca.database.util

import androidx.room.TypeConverter
import com.ca.domain.model.ReminderIteration

class ReminderIterationConverter {
    @TypeConverter
    fun toReminderIteration(text: String) = ReminderIteration.valueOf(text)

    @TypeConverter
    fun fromReminderIteration(iteration: ReminderIteration) = iteration.name
}