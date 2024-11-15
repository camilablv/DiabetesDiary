package com.ca.insulinreminder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.model.Insulin
import com.ca.model.ReminderIteration
import com.ca.domain.repository.RemindersRepository
import com.ca.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class InsulinReminderViewModel @Inject constructor(
    private val reminderRepository: RemindersRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(RecordsInsulinReminderViewState())
    val viewState: StateFlow<RecordsInsulinReminderViewState>
        get() = _viewState

    init {
        insulins()
    }

    private fun insulins() {
        viewModelScope.launch {
            settingsRepository.insulinsFlow().collect { insulins ->
                _viewState.update {
                    val selectedInsulin = insulins.getOrNull(0)
                    it.copy(
                        insulins = insulins,
                        selectedInsulin = selectedInsulin,
                        units = selectedInsulin?.defaultDose ?: 0
                    )
                }
            }
        }
    }

    fun addReminder() {
        with(_viewState.value) {
            if (selectedInsulin != null) {
                viewModelScope.launch {
                    reminderRepository.addInsulinReminder(
                        time = time,
                        iteration = iteration,
                        insulin = selectedInsulin,
                        dose = units
                    )
                }
            }
        }
    }

    fun updateReminder() {
        with(_viewState.value) {
            if (editableReminder == null) return
            viewModelScope.launch {
                reminderRepository.updateInsulinReminder(
                    editableReminder.copy(
                        time = time,
                        iteration = iteration,
                        insulinId = selectedInsulin?.id!!,
                        dose = units,
                        insulin = selectedInsulin
                    )
                )
            }
        }
    }

    fun setInsulinDropDownMenuExpanded(expanded: Boolean) {
        _viewState.update { it.copy(insulinDropDownMenuExpanded = expanded) }
    }

    fun selectInsulin(insulin: Insulin) {
        _viewState.update {
            it.copy(
                selectedInsulin = insulin,
                insulinDropDownMenuExpanded = false,
                units = insulin.defaultDose
            )
        }
    }

    fun setUnits(value: String) {
        if (value.isEmpty()) return
        _viewState.update { it.copy(units = value.toInt()) }
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

    fun setupEditMode(reminderId: Int) {
        viewModelScope.launch {
            val reminder = reminderRepository.insulinReminderById(reminderId)
            _viewState.update { state ->
                state.copy(
                    isInEditMode = true,
                    editableReminder = reminder,
                    selectedInsulin = _viewState.value.insulins.find { it.id == reminder.insulinId },
                    units = reminder.dose,
                    time = reminder.time,
                    iteration = reminder.iteration
                )
            }
        }
    }
}