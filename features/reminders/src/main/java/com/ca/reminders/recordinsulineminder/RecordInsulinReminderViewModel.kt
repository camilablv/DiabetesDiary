package com.ca.reminders.recordinsulineminder

import androidx.lifecycle.ViewModel
import com.ca.model.Insulin
import com.ca.model.ReminderIteration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalTime

class RecordInsulinReminderViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(RecordsInsulinReminderViewState())
    val viewState: StateFlow<RecordsInsulinReminderViewState>
        get() = _viewState

    fun setInsulinDropDownMenuExpanded(expanded: Boolean) {
        _viewState.update { it.copy(insulinDropDownMenuExpanded = expanded) }
    }

    fun selectInsulin(insulin: Insulin) {
        _viewState.update { it.copy(selectedInsulin = insulin, insulinDropDownMenuExpanded = false) }
    }

    fun setUnits(value: Int) {
        _viewState.update { it.copy(units = value) }
    }

    fun incrementUnits() {
        _viewState.update { it.copy(units = it.units + 1) }
    }

    fun decrementUnits() {
        _viewState.update { it.copy(units = it.units - 1) }
    }

    fun setTime(time: LocalTime) {
        _viewState.update { it.copy(time = time) }
    }

    fun setIteration(iteration: ReminderIteration) {
        _viewState.update { it.copy(iteration = iteration) }
    }
}