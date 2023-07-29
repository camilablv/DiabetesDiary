package com.ca.domain.repository

import java.time.LocalDate
import java.time.LocalTime

interface RecordGlucoseRepository {
    suspend fun recordGlucose(time: LocalTime, date: LocalDate, note: String, mark: String, units: Int)
}