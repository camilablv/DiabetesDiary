package com.ca.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.domain.repository.SettingsRepository
import com.ca.domain.usecase.GetRemindersUseCase
import com.ca.model.RecordInsulinReminder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRemindersUseCase: GetRemindersUseCase,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(HomeViewState())
    val viewState: StateFlow<HomeViewState>
        get() = _viewState

    init {
        viewModelScope.launch {
            getRemindersUseCase.invoke().collect { list ->
                val insulins = settingsRepository.insulins()
                val reminders = list.map { reminder ->
                    if (reminder is RecordInsulinReminder) {
                        reminder.copy(insulin = insulins.find { it.id == reminder.insulinId }!!)
                    } else reminder
                }.sortedBy { it.time }
                Log.d("TEST1", reminders.toString())
                _viewState.update { it.copy(reminders = reminders) }
            }
        }
    }

    fun selectDate(date: LocalDate) {
        _viewState.update { it.copy(selectedDate = date) }
    }
}