package com.ca.recordinsulin.presentation

import com.ca.model.Insulin
import com.ca.model.InsulinRecord
import java.time.LocalDate
import java.time.LocalTime

data class RecordInsulinViewState(
    val editableInsulinRecord: InsulinRecord? = null,
    val units: Int = 0,
    val time: LocalTime = LocalTime.now(),
    val date: LocalDate = LocalDate.now(),
    val selectedInsulin: Insulin? = null,
    val note: String = "",
    val insulins: List<Insulin> = listOf(),
    val showTimePicker: Boolean = false,
    val showDatePicker: Boolean = false,
    val insulinDropDownMenuExpanded: Boolean = false,
    val noteTextFieldExpanded: Boolean = false,
    val isInEditMode: Boolean = false
)
