package com.ca.database.util

import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateConverter {
    @TypeConverter
    fun toLocalDate(date: String) = LocalDate.parse(date)

    @TypeConverter
    fun fromLocalDate(date: LocalDate) = date.toString()
}