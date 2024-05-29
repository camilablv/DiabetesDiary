package com.ca.domain.repository

import com.ca.model.GlucoseRecord
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalTime

interface RecordGlucoseRepository {
    suspend fun createRecord(time: LocalTime, date: LocalDate, note: String?, mark: String, units: Int)
    suspend fun addRecord(record: GlucoseRecord)
    suspend fun updateRecord(record: GlucoseRecord)
    suspend fun records(): Flow<List<GlucoseRecord>>
    suspend fun recordsByDate(date: LocalDate): Flow<List<GlucoseRecord>>
    suspend fun delete(record: GlucoseRecord)
    suspend fun recordById(id: String): GlucoseRecord
}