package com.ca.recordinsulin.presentation

import androidx.lifecycle.ViewModel
import com.ca.model.Insulin
import com.ca.recordinsulin.domain.repository.RecordInsulinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
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
                _viewState.update { it.copy(insulins = insulins) }
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
}