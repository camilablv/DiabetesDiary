package com.ca.diabetesdiary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.diabetesdiary.domain.repository.MainRepository
import com.ca.diabetesdiary.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
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
        setStartDestination()
        checkIfOnBoardingShowed()
    }

    private fun setStartDestination() {
        //todo check if the onboarding screen was shown and show it if not
        val startDestination = if (repository.isUserSignedIn) Route.Home.route else Route.Auth.route
        _viewState.update { it.copy(startDestination = startDestination) }
    }

    private fun checkIfOnBoardingShowed() {
        viewModelScope.launch {
            val isOnBoardingShowed = repository.isOnBoardingShowed()
            _viewState.update { it.copy(isOnBoardingShowed = isOnBoardingShowed) }
        }
    }
}