package com.ca.records.insulin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ca.designsystem.components.FilterItem
import com.ca.model.InsulinRecord
import com.ca.records.domain.repository.InsulinRecordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class InsulinRecordsViewModel @Inject constructor(
    private val repository: InsulinRecordsRepository
) : ViewModel() {

    private val _viewState: MutableStateFlow<InsulinRecordsViewState> = MutableStateFlow(
        InsulinRecordsViewState()
    )
    val viewState = _viewState.asStateFlow()

    fun insulinRecords(): Flow<PagingData<InsulinRecord>> {
        return runBlocking {
            repository.records(null, 20).cachedIn(viewModelScope)
        }
    }

    fun selectDateFilter(filterItem: FilterItem) {
        _viewState.update { it.copy(selectedDateFilter = filterItem) }
    }
}