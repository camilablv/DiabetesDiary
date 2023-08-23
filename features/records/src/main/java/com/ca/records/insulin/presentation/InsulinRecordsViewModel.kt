package com.ca.records.insulin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ca.domain.model.InsulinRecord
import com.ca.records.domain.repository.InsulinRecordsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class InsulinRecordsViewModel @Inject constructor(
    private val repository: InsulinRecordsRepository
) : ViewModel() {

    fun insulinRecords(): Flow<PagingData<InsulinRecord>> {
        return runBlocking {
            repository.records(null, 20).cachedIn(viewModelScope)
        }
    }

}