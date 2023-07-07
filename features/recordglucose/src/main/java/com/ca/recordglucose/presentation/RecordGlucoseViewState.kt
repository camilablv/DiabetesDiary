package com.ca.recordglucose.presentation

import com.ca.common.utils.currentDate
import com.ca.common.utils.currentTime
import com.ca.model.GlucoseUnits
import com.ca.recordglucose.domain.model.MeasuringMark
import java.time.LocalDate
import java.time.LocalTime

data class RecordGlucoseViewState(
    val glucoseUnit: GlucoseUnits = GlucoseUnits.MMOL_PER_L,
    val glucoseLevel: Int = 0,
    val time: String = LocalTime.now().currentTime(),
    val date: String = LocalDate.now().currentDate(),
    val note: String = "",
    val measuringMark: MeasuringMark = MeasuringMark.General,
    val showTimePicker: Boolean = false,
    val showDatePicker: Boolean = false,
    val noteTextFieldExpanded: Boolean = false
)
