package com.ca.insulinreminder.presentation

import com.ca.domain.model.Insulin
import com.ca.domain.model.RecordInsulinReminder
import com.ca.domain.model.ReminderIteration
import java.time.LocalTime

data class RecordsInsulinReminderViewState(
    val editableReminder: RecordInsulinReminder? = null,
    val selectedInsulin: Insulin? = null,
    val insulins: List<Insulin> = listOf(),
    val units: Int = selectedInsulin?.defaultDose ?: 0,
    val time: LocalTime = LocalTime.now(),
    val iteration: ReminderIteration = ReminderIteration.DAILY,
    val insulinDropDownMenuExpanded: Boolean = false,
    val isInEditMode: Boolean = false
)
