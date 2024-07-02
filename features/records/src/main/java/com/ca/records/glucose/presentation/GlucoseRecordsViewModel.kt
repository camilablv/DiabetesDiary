package com.ca.records.glucose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ca.designsystem.components.FilterItem
import com.ca.model.GlucoseRecord
import com.ca.records.domain.repository.GlucoseRecordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class GlucoseRecordsViewModel @Inject constructor(
    private val repository: GlucoseRecordsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(GlucoseRecordsViewState())
    val viewState = _viewState.asStateFlow()

    fun glucoseRecords(): Flow<PagingData<GlucoseRecord>> {
        return runBlocking {
            repository.records(null, 2).cachedIn(viewModelScope)
        }
    }

    fun selectDateFilter(filterItem: FilterItem) {
        _viewState.update { it.copy(selectedDateFilter = filterItem) }
    }
}