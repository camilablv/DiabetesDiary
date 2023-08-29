package com.ca.database.util

import androidx.room.TypeConverter
import com.ca.domain.model.MeasuringMark

class MarkConverter {
    @TypeConverter
    fun toMark(text: String) = MeasuringMark.valueOf(text)

    @TypeConverter
    fun fromMark(mark: MeasuringMark) = mark.name
}