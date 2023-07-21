package com.ca.database.dao

import androidx.room.*
import com.ca.database.entities.RecordInsulinReminder

@Dao
interface InsulinReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(reminder: RecordInsulinReminder)

    @Delete
    fun delete(reminder: RecordInsulinReminder)

    @Query("SELECT * FROM insulin_reminder")
    fun insulinReminders(): List<RecordInsulinReminder>

    @Update
    fun update(reminder: RecordInsulinReminder)
}