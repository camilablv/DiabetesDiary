package com.ca.database.util

import androidx.room.TypeConverter
import com.ca.model.GlucoseUnits

class GlucoseUnitsConverter {
    @TypeConverter
    fun toGlucoseUnits(text: String) = GlucoseUnits.valueOf(text)

    @TypeConverter
    fun fromGlucoseUnits(unit: GlucoseUnits) = unit.name
}