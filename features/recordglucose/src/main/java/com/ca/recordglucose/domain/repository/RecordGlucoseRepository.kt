package com.ca.recordglucose.domain.repository

import com.ca.recordglucose.domain.model.MeasuringMark
import java.time.LocalDate
import java.time.LocalTime

interface RecordGlucoseRepository {
    suspend fun recordGlucose(time: LocalTime, date: LocalDate, note: String, mark: MeasuringMark, units: Int)
}