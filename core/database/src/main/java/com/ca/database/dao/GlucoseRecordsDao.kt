package com.ca.database.dao

import androidx.room.*
import com.ca.database.model.GlucoseRecordEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface GlucoseRecordsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: GlucoseRecordEntity)

    @Delete
    suspend fun delete(record: GlucoseRecordEntity)

    @Query("SELECT * FROM glucose_record")
    fun insulinRecords(): Flow<List<GlucoseRecordEntity>>

    @Update
    fun update(record: GlucoseRecordEntity)

    @Query("SELECT * FROM glucose_record WHERE date == :date")
    fun recordsByDate(date: LocalDate): Flow<List<GlucoseRecordEntity>>
}