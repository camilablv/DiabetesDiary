package com.ca.recordinsulin.presentation

import com.ca.common.utils.currentDate
import com.ca.common.utils.currentTime
import com.ca.model.Insulin

data class InsulinViewState(
    val units: Int = 0,
    val time: String = currentTime(),
    val date: String = currentDate(),
    val selectedInsulin: Insulin? = null,
    val note: String = "",
    val insulins: List<Insulin> = listOf(),
    val showTimePicker: Boolean = false,
    val showDatePicker: Boolean = false,
    val insulinDropDownMenuExpanded: Boolean = false,
    val noteTextFieldExpanded: Boolean = false
)
