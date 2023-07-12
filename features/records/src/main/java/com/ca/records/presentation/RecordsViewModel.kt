package com.ca.records.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.records.domain.repository.RecordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordsViewModel @Inject constructor(
    private val repository: RecordsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(RecordsViewState())
    val viewState: StateFlow<RecordsViewState>
        get() = _viewState

    init {
        viewModelScope.launch {
            repository.insulinRecords(null, 20)?.let { list ->
                _viewState.update { it.copy(insulinRecords = list) }
            }
        }
    }
}