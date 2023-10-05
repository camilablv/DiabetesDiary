package com.ca.database.dao

import androidx.room.*
import com.ca.database.model.RecordGlucoseReminderEntity
import com.ca.database.model.glucoseRemindersTableName
import kotlinx.coroutines.flow.Flow

@Dao
interface GlucoseReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(reminder: RecordGlucoseReminderEntity): Long

    @Delete
    fun delete(reminder: RecordGlucoseReminderEntity)

    @Query("SELECT * FROM $glucoseRemindersTableName")
    fun glucoseReminders(): Flow<List<RecordGlucoseReminderEntity>>

    @Update
    fun update(reminder: RecordGlucoseReminderEntity)

    @Query("SELECT * FROM $glucoseRemindersTableName WHERE id == :id")
    fun reminderById(id: Int): RecordGlucoseReminderEntity
}