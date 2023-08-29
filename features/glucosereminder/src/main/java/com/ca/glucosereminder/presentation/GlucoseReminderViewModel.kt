package com.ca.glucosereminder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.domain.model.ReminderIteration
import com.ca.domain.repository.RemindersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class GlucoseReminderViewModel @Inject constructor(
    private val reminderRepository: RemindersRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(GlucoseReminderViewState())
    val viewState: StateFlow<GlucoseReminderViewState>
        get() = _viewState

    fun addReminder() {
        with(_viewState.value) {
            viewModelScope.launch {
                reminderRepository.addRecordGlucoseReminder(
                    time = time,
                    iteration = iteration
                )
            }
        }
    }

    fun updateReminder() {
        with(_viewState.value) {
            if (editableReminder == null) return
            viewModelScope.launch {
                reminderRepository.updateGlucoseReminder(
                    editableReminder.copy(
                        time = time,
                        iteration = iteration
                    )
                )
            }
        }
    }

    fun setTime(time: LocalTime) {
        _viewState.update { it.copy(time = time) }
    }

    fun setIteration(iteration: ReminderIteration) {
        _viewState.update { it.copy(iteration = iteration) }
    }

    fun setupEditMode(reminderId: Int) {
        viewModelScope.launch {
            val reminder = reminderRepository.glucoseReminderById(reminderId)
            _viewState.update {
                it.copy(
                    isInEditMode = true,
                    editableReminder = reminder,
                    time = reminder.time,
                    iteration = reminder.iteration
                )
            }
        }
    }
}