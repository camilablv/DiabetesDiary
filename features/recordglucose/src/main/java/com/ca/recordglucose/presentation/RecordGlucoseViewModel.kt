package com.ca.recordglucose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.domain.model.MeasuringMark
import com.ca.domain.repository.RecordGlucoseRepository
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
            addRecord(time, date, note.ifEmpty { null }, measuringMark, glucoseLevel)
        }
    }

    fun updateRecord() {
        with(_viewState.value) {
            if (editableRecord == null) return
            viewModelScope.launch {
                repository.updateRecord(
                    editableRecord.copy(
                        level = glucoseLevel.toDouble(),
                        time = time,
                        date = date,
                        note = note,
                        measuringMark = measuringMark
                    )
                )
            }
        }
    }

    private fun addRecord(time: LocalTime, date: LocalDate, note: String?, mark: MeasuringMark, glucoseLevel: Int) {
        viewModelScope.launch {
            repository.createRecord(time, date, note, mark.name, glucoseLevel)
        }
    }

    fun setNote(text: String) {
        if (text.count() > 140) return
        _viewState.update { it.copy(note = text) }
    }

    fun setGlucoseLevel(value: String) {
        _viewState.update { it.copy(glucoseLevel = value.toInt()) }
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

    fun setupEditMode(recordId: String) {
        viewModelScope.launch {
            val record = repository.recordById(recordId)
            _viewState.update {
                it.copy(
                    isInEditMode = true,
                    editableRecord = record,
                    glucoseLevel = record.level.toInt(),
                    time = record.time,
                    date = record.date,
                    note = record.note ?: "",
                    measuringMark = record.measuringMark
                )
            }
        }
    }
}