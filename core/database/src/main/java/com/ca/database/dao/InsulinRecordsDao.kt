package com.ca.database.dao

import androidx.room.*
import com.ca.database.model.InsulinRecordEntity
import com.ca.database.model.insulinRecordsTableName
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface InsulinRecordsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: InsulinRecordEntity)

    @Delete
    suspend fun delete(record: InsulinRecordEntity)

    @Query("SELECT * FROM $insulinRecordsTableName")
    fun insulinRecords(): Flow<List<InsulinRecordEntity>>

    @Update
    fun update(record: InsulinRecordEntity)

    @Query("SELECT * FROM $insulinRecordsTableName WHERE date == :date")
    fun recordsByDate(date: LocalDate): Flow<List<InsulinRecordEntity>>

    @Query("SELECT * FROM $insulinRecordsTableName WHERE id == :id")
    fun recordsById(id: String): InsulinRecordEntity
}