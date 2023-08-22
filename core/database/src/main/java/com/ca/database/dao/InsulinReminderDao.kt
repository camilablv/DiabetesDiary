package com.ca.database.dao

import androidx.room.*
import com.ca.database.model.RecordInsulinReminderEntity
import com.ca.database.model.insulinRemindersTableName
import kotlinx.coroutines.flow.Flow

@Dao
interface InsulinReminderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminder: RecordInsulinReminderEntity): Long

    @Delete
    suspend fun delete(reminder: RecordInsulinReminderEntity)

    @Query("SELECT * FROM $insulinRemindersTableName")
    fun insulinReminders(): Flow<List<RecordInsulinReminderEntity>>

    @Query("SELECT * FROM $insulinRemindersTableName")
    fun insulinRemindersFlow(): Flow<RecordInsulinReminderEntity>

    @Update
    fun update(reminder: RecordInsulinReminderEntity)

    @Query("SELECT * FROM $insulinRemindersTableName WHERE insulin_id == :insulinId")
    fun insulinRemindersByInsulinId(insulinId: String): List<RecordInsulinReminderEntity>

    @Query("SELECT * FROM $insulinRemindersTableName WHERE id == :id")
    suspend fun insulinReminderById(id: Int): RecordInsulinReminderEntity
}