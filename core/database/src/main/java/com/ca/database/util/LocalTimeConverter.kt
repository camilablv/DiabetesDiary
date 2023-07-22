package com.ca.database.util

import androidx.room.TypeConverter
import java.time.LocalTime

class LocalTimeConverter {
    @TypeConverter
    fun toLocalTime(time: String) = LocalTime.parse(time)

    @TypeConverter
    fun fromLocalTime(time: LocalTime) = time.toString()
}