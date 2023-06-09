package com.ca.diabetesdiary.presentation

import androidx.lifecycle.ViewModel
import com.ca.diabetesdiary.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    fun isUserSignedIn() = repository.isUserSignedIn

    fun signInAnonymously() {
        repository.signInAnonymously()
    }
}