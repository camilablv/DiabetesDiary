package com.ca.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.domain.repository.SettingsRepository
import com.ca.domain.usecase.GetRecordsByDateUseCase
import com.ca.domain.usecase.GetRemindersUseCase
import com.ca.domain.usecase.MarkInsulinReminderAsDoneUseCase
import com.ca.domain.usecase.RemoveItemUseCase
import com.ca.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val removeItemUseCase: RemoveItemUseCase,
    private val getRemindersUseCase: GetRemindersUseCase,
    private val getRecordsUseCase: GetRecordsByDateUseCase,
    private val markInsulinReminderAsDoneUseCase: MarkInsulinReminderAsDoneUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(HomeViewState())
    val viewState: StateFlow<HomeViewState>
        get() = _viewState

    init {
        items(viewState.value.selectedDate)
    }

    fun selectDate(date: LocalDate) {
        _viewState.update { it.copy(selectedDate = date) }
        items(date)
    }

    private fun items(date: LocalDate) {
        viewModelScope.launch {
            val reminders = getRemindersUseCase()
            val records = getRecordsUseCase(date)
            records.combine(reminders) { recordItems, reminderItems ->
                if (date == LocalDate.now()) {
                    val insulins = settingsRepository.insulins()
                    val list = reminderItems.map { reminder ->
                        if (reminder is RecordInsulinReminder) {
                            reminder.copy(insulin = insulins.find { it.id == reminder.insulinId }!!)
                        } else reminder
                    }
                    recordItems.plus(list)
                } else recordItems
            }.collect { list ->
                _viewState.update { state ->
                    state.copy(
                        listItems = list
                            .sortedBy { it.time }
                    )
                }
            }
        }

    }

    fun markInsulinReminderAsDone(reminder: RecordInsulinReminder) {
        viewModelScope.launch {
            markInsulinReminderAsDoneUseCase(reminder)
        }
    }
}

