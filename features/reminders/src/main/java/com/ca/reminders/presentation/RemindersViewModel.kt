package com.ca.reminders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.domain.repository.RemindersRepository
import com.ca.domain.repository.SettingsRepository
import com.ca.domain.usecase.RemoveItemUseCase
import com.ca.model.ListItem
import com.ca.model.RecordGlucoseReminder
import com.ca.model.RecordInsulinReminder
import com.ca.platform.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RemindersViewModel @Inject constructor(
    private val reminderRepository: RemindersRepository,
    private val settingsRepository: SettingsRepository,
    private val removeItemUseCase: RemoveItemUseCase
) : BaseViewModel() {

    private val _viewState = MutableStateFlow(RemindersViewState())
    val viewState: StateFlow<RemindersViewState>
        get() = _viewState

    init {
        viewModelScope.launch {
            reminderRepository.insulinReminders().collect { list ->
                val insulins = settingsRepository.insulins()
                val reminders = list.map { reminder ->
                    reminder.copy(insulin = insulins.find { it.id == reminder.insulinId }!!)
                }.sortedBy { it.time }
                _viewState.update { it.copy(insulinReminders = reminders) }
            }
        }
        viewModelScope.launch {
            reminderRepository.glucoseReminders().collect { list ->
                val sortedReminders = list.sortedBy { it.time }
                _viewState.update { it.copy(glucoseReminders = sortedReminders) }
            }
        }
    }

    fun setInsulinReminderEnabled(reminder: RecordInsulinReminder, enabled: Boolean) {
        viewModelScope.launch {
            reminderRepository.updateInsulinReminder(reminder.copy(enabled = enabled))
        }
    }

    fun setGlucoseReminderEnabled(reminder: RecordGlucoseReminder, enabled: Boolean) {
        viewModelScope.launch {
            reminderRepository.updateGlucoseReminder(reminder.copy(enabled = enabled))
        }
    }

    override fun removeItem(item: ListItem?) {
        viewModelScope.launch {
            item?.let { removeItemUseCase(item) }
        }
        disableEditMode()
    }
}