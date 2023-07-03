package com.ca.diabetesdiary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.diabetesdiary.domain.repository.MainRepository
import com.ca.diabetesdiary.navigation.Route
import com.ca.diabetesdiary.presentation.state.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainViewState())
    val viewState: StateFlow<MainViewState> = _viewState

    init {
        setStartDestination()
    }

    private fun setStartDestination() {
        viewModelScope.launch {
            val isOnBoardingShowed = checkIfOnBoardingShowed()
            val startDestination = if (!repository.isUserSignedIn) Route.Auth.route
            else if (!isOnBoardingShowed) Route.OnBoarding.route
            else Route.Home.route

            _viewState.update { it.copy(startDestination = startDestination) }
        }
    }

    private fun checkIfOnBoardingShowed(): Boolean {
        return runBlocking {
            repository.isOnBoardingShowed().also { isOnBoardingShowed ->
                _viewState.update { it.copy(shouldShowOnBoarding = !isOnBoardingShowed) }
            }
        }
    }
}