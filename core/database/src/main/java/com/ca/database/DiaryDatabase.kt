package com.ca.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ca.database.dao.GlucoseRecordsDao
import com.ca.database.dao.GlucoseReminderDao
import com.ca.database.dao.InsulinRecordsDao
import com.ca.database.dao.InsulinReminderDao
import com.ca.database.model.GlucoseRecordEntity
import com.ca.database.model.InsulinRecordEntity
import com.ca.database.model.RecordGlucoseReminderEntity
import com.ca.database.model.RecordInsulinReminderEntity
import com.ca.database.util.*

@Database(
    entities = [
        RecordGlucoseReminderEntity::class,
        RecordInsulinReminderEntity::class,
        InsulinRecordEntity::class,
        GlucoseRecordEntity::class
    ],
    version = 2
)
@TypeConverters(
    LocalTimeConverter::class,
    ReminderIterationConverter::class,
    LocalDateConverter::class,
    GlucoseUnitsConverter::class
)
abstract class DiaryDatabase : RoomDatabase() {
    abstract fun glucoseReminderDao(): GlucoseReminderDao
    abstract fun insulinReminderDao(): InsulinReminderDao
    abstract fun insulinRecordsDao(): InsulinRecordsDao
    abstract fun glucoseRecordsDao(): GlucoseRecordsDao
}