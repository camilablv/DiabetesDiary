package com.ca.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.common.utils.getNextDates
import com.ca.common.utils.getPrevDates
import com.ca.common.utils.weekStartDate
import com.ca.designsystem.components.singlerowcalendar.CalendarState
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
    val visibleDates: StateFlow<CalendarState> = _visibleDates

    private fun calculateCollapsedCalendarDays(startDate: LocalDate): CalendarState {
        val dates = startDate.getNextDates(21)
        val array =  Array(3) {
            dates.slice(it * 7 until (it + 1) * 7)
        }
        return CalendarState(
            prevWeek = array[0],
            currentWeek = array[1],
            nextWeek = array[2]
        )
    }

    fun loadDates(page: Int): List<LocalDate> {
        return LocalDate.now().weekStartDate().getPrevDates(page)
    }

    fun loadNextDates() {
        calculateCalendarDates(startDate = visibleDates.value.currentWeek[0])
    }

    fun loadPrevDates() {
        calculateCalendarDates(startDate = visibleDates.value.prevWeek[0].minusDays(7))
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