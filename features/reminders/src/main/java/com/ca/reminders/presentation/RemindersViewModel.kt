package com.ca.reminders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.domain.repository.InsulinReminderRepository
import com.ca.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RemindersViewModel @Inject constructor(
    private val repository: InsulinReminderRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(RemindersViewState())
    val viewState: StateFlow<RemindersViewState>
        get() = _viewState

    init {
        viewModelScope.launch {
            repository.reminders().collect { list ->
                val insulins = settingsRepository.insulins()
                val reminders = list.map { reminder ->
                    reminder to insulins.find { it.id == reminder.insulinId }!!
                }.sortedBy { it.first.time }
                _viewState.update { it.copy(insulinReminders = reminders) }
            }
        }
    }
}