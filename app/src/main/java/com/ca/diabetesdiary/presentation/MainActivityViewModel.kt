package com.ca.diabetesdiary.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.diabetesdiary.domain.repository.MainRepository
import com.ca.diabetesdiary.navigation.MainRoute
import com.ca.diabetesdiary.presentation.state.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
        setTheme()
    }

    private fun setStartDestination() {
        viewModelScope.launch {
            val isOnBoardingShowed = checkIfOnBoardingShowed()
            val startDestination = if (!repository.isUserSignedIn) MainRoute.Auth.route
            else if (!isOnBoardingShowed) MainRoute.OnBoarding.route
            else MainRoute.Home.route

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

    private fun setTheme() {
        viewModelScope.launch {
            repository.darkMode().collect { darkMode ->
                _viewState.update { it.copy(darkMode = darkMode) }
            }
        }
    }
}