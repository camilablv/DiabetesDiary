package com.ca.diabetesdiary.presentation

import androidx.lifecycle.ViewModel
import com.ca.diabetesdiary.data.repository.MainRepository
import com.ca.diabetesdiary.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState: StateFlow<MainViewState> = _viewState

    init {
        val startDestination = if (repository.isUserSignedIn) Route.Home.route else Route.Auth.route
        _viewState.update { it.copy(startDestination = startDestination) }
    }

    fun signInAnonymously() {
        repository.signInAnonymously()
    }
}