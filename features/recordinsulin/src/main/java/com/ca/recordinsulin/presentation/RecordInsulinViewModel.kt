package com.ca.recordinsulin.presentation

import androidx.lifecycle.ViewModel
import com.ca.common.utils.currentDate
import com.ca.common.utils.currentTime
import com.ca.model.Insulin
import com.ca.recordinsulin.domain.repository.RecordInsulinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class RecordInsulinViewModel @Inject constructor(
    private val repository: RecordInsulinRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(InsulinViewState())
    val viewState: StateFlow<InsulinViewState>
        get() = _viewState

    init {
        runBlocking {
            repository.insulins().let { insulins ->
                _viewState.update { it.copy(insulins = insulins, selectedInsulin = insulins[0]) }
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
        _viewState.update { it.copy(note = text) }
    }

    fun setUnits(value: Int) {
        _viewState.update { it.copy(units = value) }
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
        _viewState.update { it.copy(time = time.currentTime()) }
    }

    fun showDatePicker(show: Boolean) {
        _viewState.update { it.copy(showDatePicker = show) }
    }

    fun setDate(date: LocalDate) {
        _viewState.update { it.copy(date = date.currentDate()) }
    }

    fun addRecord() {

    }
}