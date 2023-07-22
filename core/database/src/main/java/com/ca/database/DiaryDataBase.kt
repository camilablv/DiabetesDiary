package com.ca.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ca.database.util.LocalTimeConverter
import com.ca.database.util.ReminderIterationConverter
import com.ca.database.dao.GlucoseReminderDao
import com.ca.database.dao.InsulinReminderDao
import com.ca.model.RecordGlucoseReminder
import com.ca.model.RecordInsulinReminder

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
abstract class DiaryDataBase : RoomDatabase() {
    abstract fun glucoseReminderDao(): GlucoseReminderDao
    abstract fun insulinReminderDao(): InsulinReminderDao
}