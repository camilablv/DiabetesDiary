package com.ca.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ca.database.converters.LocalTimeConverter
import com.ca.database.converters.ReminderIterationConverter
import com.ca.database.dao.GlucoseReminderDao
import com.ca.database.dao.InsulinReminderDao
import com.ca.database.entities.RecordGlucoseReminder
import com.ca.database.entities.RecordInsulinReminder

@Database(
    entities = [
        RecordGlucoseReminder::class,
        RecordInsulinReminder::class
    ],
    version = 2
)
@TypeConverters(
    LocalTimeConverter::class,
    ReminderIterationConverter::class
)
abstract class ReminderDataBase : RoomDatabase() {
    abstract fun glucoseReminderDao(): GlucoseReminderDao
    abstract fun insulinReminderDao(): InsulinReminderDao
}