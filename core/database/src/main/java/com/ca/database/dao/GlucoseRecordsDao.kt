package com.ca.database.dao

import androidx.room.*
import com.ca.database.model.GlucoseRecordEntity
import com.ca.database.model.glucoseRecordsTableName
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface GlucoseRecordsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(record: GlucoseRecordEntity)

    @Delete
    fun delete(record: GlucoseRecordEntity)

    @Query("SELECT * FROM $glucoseRecordsTableName")
    fun glucoseRecords(): Flow<List<GlucoseRecordEntity>>

    @Update
    fun update(record: GlucoseRecordEntity)

    @Query("SELECT * FROM $glucoseRecordsTableName WHERE date == :date")
    fun recordsByDate(date: LocalDate): Flow<List<GlucoseRecordEntity>>

    @Query("SELECT * FROM $glucoseRecordsTableName WHERE id == :id")
    fun recordById(id: String): GlucoseRecordEntity
}