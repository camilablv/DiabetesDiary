package com.ca.database.dao

import androidx.room.*
import com.ca.database.entities.RecordGlucoseReminder

@Dao
interface GlucoseReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(reminder: RecordGlucoseReminder)

    @Delete
    fun delete(reminder: RecordGlucoseReminder)

    @Query("SELECT * FROM insulin_reminder")
    fun glucoseReminders(): List<RecordGlucoseReminder>

    @Update
    fun update(reminder: RecordGlucoseReminder)
}