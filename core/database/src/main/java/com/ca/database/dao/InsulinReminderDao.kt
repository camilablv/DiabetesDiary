package com.ca.database.dao

import androidx.room.*
import com.ca.database.model.RecordInsulinReminderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InsulinReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminder: RecordInsulinReminderEntity)

    @Delete
    suspend fun delete(reminder: RecordInsulinReminderEntity)

    @Query("SELECT * FROM insulin_reminder")
    fun insulinReminders(): Flow<List<RecordInsulinReminderEntity>>

    @Update
    fun update(reminder: RecordInsulinReminderEntity)
}