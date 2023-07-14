package com.ca.records.glucose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ca.model.InsulinRecord
import com.ca.records.di.GlucoseRecordsRepository
import com.ca.records.domain.repository.RecordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class GlucoseRecordsViewModel @Inject constructor(
    @GlucoseRecordsRepository private val repository: RecordsRepository
) : ViewModel() {

    fun glucoseRecords(): Flow<PagingData<InsulinRecord>> {
        return runBlocking {
            repository.records(null, 2).cachedIn(viewModelScope)
        }
    }
}