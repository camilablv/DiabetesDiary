package com.ca.reminders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.domain.repository.InsulinReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RemindersViewModel @Inject constructor(
    private val repository: InsulinReminderRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(RemindersViewState())
    val viewState: StateFlow<RemindersViewState>
        get() = _viewState

    init {
        viewModelScope.launch {
            repository.reminders().collect { list ->
                _viewState.update { it.copy(insulinReminders = list) }
            }
        }
    }
}