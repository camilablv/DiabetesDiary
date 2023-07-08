package com.ca.recordglucose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.recordglucose.domain.model.MeasuringMark
import com.ca.recordglucose.domain.repository.RecordGlucoseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class RecordGlucoseViewModel @Inject constructor(
    private val repository: RecordGlucoseRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(RecordGlucoseViewState())
    val viewState: StateFlow<RecordGlucoseViewState>
        get() = _viewState

    fun addRecord() {
        with(viewState.value) {
            addRecord(time, date, note, measuringMark, glucoseLevel)
        }
    }

    private fun addRecord(time: LocalTime, date: LocalDate, note: String, mark: MeasuringMark, glucoseLevel: Int) {
        viewModelScope.launch {
            repository.recordGlucose(time, date, note, mark, glucoseLevel)
        }
    }

    fun setNote(text: String) {
        _viewState.update { it.copy(note = text) }
    }

    fun setGlucoseLevel(value: Int) {
        _viewState.update { it.copy(glucoseLevel = value) }
    }

    fun incrementGlucoseLevel() {
        _viewState.update { it.copy(glucoseLevel = it.glucoseLevel + 1) }
    }

    fun decrementGlucoseLevel() {
        _viewState.update { it.copy(glucoseLevel = it.glucoseLevel - 1) }
    }

    fun showTimePicker(show: Boolean) {
        _viewState.update { it.copy(showTimePicker = show) }
    }

    fun setTime(time: LocalTime) {
        _viewState.update { it.copy(time = time) }
    }

    fun showDatePicker(show: Boolean) {
        _viewState.update { it.copy(showDatePicker = show) }
    }

    fun setDate(date: LocalDate) {
        _viewState.update { it.copy(date = date) }
    }

    fun setMeasuringMark(mark: MeasuringMark) {
        _viewState.update { it.copy(measuringMark = mark) }
    }
}