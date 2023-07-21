package com.ca.insulinreminder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.insulinreminder.domain.InsulinReminderRepository
import com.ca.model.Insulin
import com.ca.model.ReminderIteration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class InsulinReminderViewModel @Inject constructor(
    private val repository: InsulinReminderRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(RecordsInsulinReminderViewState())
    val viewState: StateFlow<RecordsInsulinReminderViewState>
        get() = _viewState

    init {
        runBlocking {
            repository.insulins().let { insulins ->
                _viewState.update { it.copy(insulins = insulins, selectedInsulin = insulins[0]) }
            }
        }
    }

    fun addReminder() {
        with(_viewState.value) {
            if (selectedInsulin != null) {
                viewModelScope.launch {
                    repository.addReminder(
                        time = time,
                        iteration = iteration,
                        insulin = selectedInsulin,
                        dose = units
                    )
                }
            }
        }
    }

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