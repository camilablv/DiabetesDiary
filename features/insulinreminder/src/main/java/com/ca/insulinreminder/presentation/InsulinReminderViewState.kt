package com.ca.insulinreminder.presentation

import com.ca.model.Insulin
import com.ca.model.ReminderIteration
import java.time.LocalTime

data class RecordsInsulinReminderViewState(
    val selectedInsulin: Insulin? = null,
    val insulins: List<Insulin> = listOf(),
    val units: Int = 0,
    val time: LocalTime = LocalTime.now(),
    val iteration: ReminderIteration = ReminderIteration.ONCE,
    val insulinDropDownMenuExpanded: Boolean = false,
)
