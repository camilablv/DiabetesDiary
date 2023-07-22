package com.ca.database.dao

import androidx.room.*
import com.ca.model.RecordGlucoseReminder
import kotlinx.coroutines.flow.Flow

@Dao
interface GlucoseReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminder: RecordGlucoseReminder)

    @Delete
    suspend fun delete(reminder: RecordGlucoseReminder)

    @Query("SELECT * FROM insulin_reminder")
    fun glucoseReminders(): Flow<List<RecordGlucoseReminder>>

    @Update
    fun update(reminder: RecordGlucoseReminder)
}