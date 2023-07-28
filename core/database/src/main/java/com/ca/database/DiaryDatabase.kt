package com.ca.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ca.database.dao.GlucoseReminderDao
import com.ca.database.dao.InsulinReminderDao
import com.ca.database.model.RecordGlucoseReminderEntity
import com.ca.database.model.RecordInsulinReminderEntity
import com.ca.database.util.LocalTimeConverter
import com.ca.database.util.ReminderIterationConverter

@Database(
    entities = [
        RecordGlucoseReminderEntity::class,
        RecordInsulinReminderEntity::class
    ],
    version = 2
)
@TypeConverters(
    LocalTimeConverter::class,
    ReminderIterationConverter::class
)
abstract class DiaryDatabase : RoomDatabase() {
    abstract fun glucoseReminderDao(): GlucoseReminderDao
    abstract fun insulinReminderDao(): InsulinReminderDao
}