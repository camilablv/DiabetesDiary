package com.ca.diabetesdiary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.diabetesdiary.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    fun updateUserId(id: String) {
        viewModelScope.launch {
            repository.updateId(id)
        }
    }
}