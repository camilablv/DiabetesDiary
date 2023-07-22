package com.ca.database.dao

import androidx.room.*
import com.ca.model.RecordInsulinReminder
import kotlinx.coroutines.flow.Flow

@Dao
interface InsulinReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminder: RecordInsulinReminder)

    @Delete
    suspend fun delete(reminder: RecordInsulinReminder)

    @Query("SELECT * FROM insulin_reminder")
    fun insulinReminders(): Flow<List<RecordInsulinReminder>>

    @Update
    fun update(reminder: RecordInsulinReminder)
}