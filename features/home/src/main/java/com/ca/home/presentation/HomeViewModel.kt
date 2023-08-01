package com.ca.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.common.utils.getNextDates
import com.ca.common.utils.startDates
import com.ca.common.utils.weekStartDate
import com.ca.domain.repository.SettingsRepository
import com.ca.domain.usecase.GetRemindersUseCase
import com.ca.model.RecordInsulinReminder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRemindersUseCase: GetRemindersUseCase,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(HomeViewState())
    val viewState: StateFlow<HomeViewState>
        get() = _viewState

    private val _visibleDates =
        MutableStateFlow(
            calculateCollapsedCalendarDays(
                startDate = LocalDate.now().weekStartDate().minusWeeks(1)
            )
        )
    val visibleDates: StateFlow<Array<List<LocalDate>>> = _visibleDates

    private fun calculateCollapsedCalendarDays(startDate: LocalDate): Array<List<LocalDate>> {
        val dates = startDate.getNextDates(21)
        return Array(3) {
            dates.slice(it * 7 until (it + 1) * 7)
        }
    }

    fun loadNexDates(startDate: LocalDate) {
        calculateCalendarDates(startDate = startDate)
    }

    private fun calculateCalendarDates(
        startDate: LocalDate
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _visibleDates.emit(
                calculateCollapsedCalendarDays(startDate)
            )
        }
    }

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
}