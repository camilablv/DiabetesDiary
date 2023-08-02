package com.ca.domain.repository

import com.ca.database.model.InsulinRecordEntity
import com.ca.model.Insulin
import com.ca.model.InsulinRecord
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalTime

interface RecordInsulinRepository {
    suspend fun insulins(): List<Insulin>
    suspend fun recordInsulin(insulinId: String, note: String, date: LocalDate, time: LocalTime, dose: Int)
    suspend fun addRecord(record: InsulinRecord)
    suspend fun records(): Flow<List<InsulinRecord>>
    suspend fun recordsByDate(date: LocalDate): Flow<List<InsulinRecord>>
}