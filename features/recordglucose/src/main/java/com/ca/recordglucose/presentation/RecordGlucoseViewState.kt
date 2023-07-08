package com.ca.recordglucose.presentation

import com.ca.recordglucose.domain.model.MeasuringMark
import java.time.LocalDate
import java.time.LocalTime

data class RecordGlucoseViewState(
    val glucoseLevel: Int = 0,
    val time: LocalTime = LocalTime.now(),
    val date: LocalDate = LocalDate.now(),
    val note: String = "",
    val measuringMark: MeasuringMark = MeasuringMark.GENERAL,
    val showTimePicker: Boolean = false,
    val showDatePicker: Boolean = false,
    val noteTextFieldExpanded: Boolean = false
)
