package com.ca.diabetesdiary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.diabetesdiary.data.repository.MainRepository
import com.ca.diabetesdiary.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState: StateFlow<MainViewState> = _viewState

    init {
        val startDestination = if (isUserSignedIn()) Route.Home.route else Route.GetStarted.route
        _viewState.update { it.copy(startDestination = startDestination) }
    }

    private fun isUserSignedIn() = repository.isUserSignedIn

    fun signInAnonymously() {
        repository.signInAnonymously()
    }
}