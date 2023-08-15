package com.ca.database.dao

import androidx.room.*
import com.ca.database.model.RecordGlucoseReminderEntity
import com.ca.database.model.glucoseReminderTableName
import kotlinx.coroutines.flow.Flow

@Dao
interface GlucoseReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminder: RecordGlucoseReminderEntity)

    @Delete
    suspend fun delete(reminder: RecordGlucoseReminderEntity)

    @Query("SELECT * FROM $glucoseReminderTableName")
    fun glucoseReminders(): Flow<List<RecordGlucoseReminderEntity>>

    @Update
    fun update(reminder: RecordGlucoseReminderEntity)

    @Query("SELECT * FROM $glucoseReminderTableName WHERE id == :id")
    suspend fun reminderById(id: Int): RecordGlucoseReminderEntity
}