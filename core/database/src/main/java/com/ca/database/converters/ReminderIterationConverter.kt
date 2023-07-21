package com.ca.database.converters

import androidx.room.TypeConverter
import com.ca.model.ReminderIteration

class ReminderIterationConverter {
    @TypeConverter
    fun toInsulin(text: String) = ReminderIteration.valueOf(text)

    @TypeConverter
    fun fromInsulin(iteration: ReminderIteration) = iteration.name
}