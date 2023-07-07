package com.ca.recordglucose.presentation

import androidx.lifecycle.ViewModel
import com.ca.common.utils.currentDate
import com.ca.common.utils.currentTime
import com.ca.model.GlucoseUnits
import com.ca.recordglucose.domain.model.MeasuringMark
import com.ca.recordglucose.domain.repository.RecordGlucoseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
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
        _viewState.update { it.copy(time = time.currentTime()) }
    }

    fun showDatePicker(show: Boolean) {
        _viewState.update { it.copy(showDatePicker = show) }
    }

    fun setDate(date: LocalDate) {
        _viewState.update { it.copy(date = date.currentDate()) }
    }

    fun setGlucoseUnit(unit: GlucoseUnits) {
        _viewState.update { it.copy(glucoseUnit = unit) }
    }

    fun setMeasuringMark(mark: MeasuringMark) {
        _viewState.update { it.copy(measuringMark = mark) }
    }
}