package com.ca.records.glucose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ca.domain.model.GlucoseRecord
import com.ca.records.domain.repository.GlucoseRecordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class GlucoseRecordsViewModel @Inject constructor(
    private val repository: GlucoseRecordsRepository
) : ViewModel() {

    fun glucoseRecords(): Flow<PagingData<GlucoseRecord>> {
        return runBlocking {
            repository.records(null, 2).cachedIn(viewModelScope)
        }
    }
}