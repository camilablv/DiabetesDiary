package com.ca.authentication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.authentication.domain.repository.AuthRepository
import com.ca.authentication.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    fun signInWithGoogle(token: String) {
        viewModelScope.launch {
            repository.signInWithGoogle(token)
        }
    }

    fun createUser(userData: UserData) {
        repository.createUser(userData)
    }
}