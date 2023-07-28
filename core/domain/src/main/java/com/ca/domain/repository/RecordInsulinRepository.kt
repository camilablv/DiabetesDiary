package com.ca.domain.repository

import com.ca.model.Insulin
import java.time.LocalDate
import java.time.LocalTime

interface RecordInsulinRepository {
    suspend fun insulins(): List<Insulin>
    suspend fun recordInsulin(insulinId: String, note: String, date: LocalDate, time: LocalTime, dose: Int)
}