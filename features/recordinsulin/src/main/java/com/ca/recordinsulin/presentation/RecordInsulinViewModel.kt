package com.ca.recordinsulin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.domain.model.Insulin
import com.ca.domain.repository.RecordInsulinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class RecordInsulinViewModel @Inject constructor(
    private val repository: RecordInsulinRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(RecordInsulinViewState())
    val viewState: StateFlow<RecordInsulinViewState>
        get() = _viewState

    init {
        runBlocking {
            repository.insulins().let { insulins ->
                _viewState.update {
                    it.copy(
                        insulins = insulins,
                        selectedInsulin = insulins.getOrNull(0)
                    )
                }
            }
        }
    }

    fun addRecord() {
        with(viewState.value) {
            addRecord(selectedInsulin?.id!!, note, date, time, units)
        }
    }

    fun updateRecord() {
        with(_viewState.value) {
            if (editableInsulinRecord == null) return
            viewModelScope.launch {
                repository.updateRecord(
                    editableInsulinRecord.copy(
                        insulin = selectedInsulin!!,
                        time = time,
                        date = date,
                        units = units.toDouble(),
                        note = note
                    )
                )
            }
        }
    }

    fun setInsulinDropDownMenuExpanded(expanded: Boolean) {
        _viewState.update { it.copy(insulinDropDownMenuExpanded = expanded) }
    }

    fun selectInsulin(insulin: Insulin) {
        _viewState.update { it.copy(selectedInsulin = insulin, insulinDropDownMenuExpanded = false) }
    }

    fun setNote(text: String) {
        if (text.count() > 140) return
        _viewState.update { it.copy(note = text) }
    }

    fun setUnits(value: String) {
        _viewState.update { it.copy(units = value.toInt()) }
    }

    fun incrementUnits() {
        _viewState.update { it.copy(units = it.units + 1) }
    }

    fun decrementUnits() {
        _viewState.update { it.copy(units = it.units - 1) }
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

    private fun addRecord(insulinId: String, note: String, date: LocalDate, time: LocalTime, units: Int) {
        viewModelScope.launch {
            repository.createRecord(insulinId, note, date, time, units)
        }
    }

    fun setupEditMode(recordId: String) {
        viewModelScope.launch {
            val record = repository.recordById(recordId)
            _viewState.update {
                it.copy(
                    isInEditMode = true,
                    editableInsulinRecord = record,
                    units = record.units.toInt(),
                    time = record.time,
                    date = record.date,
                    selectedInsulin = record.insulin,
                    note = record.note ?: ""
                )
            }
        }
    }
}