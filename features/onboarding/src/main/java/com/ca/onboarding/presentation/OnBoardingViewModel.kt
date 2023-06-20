package com.ca.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.settings.domain.model.GlucoseUnits
import com.ca.settings.domain.model.Insulin
import com.ca.settings.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val repository: SettingsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(OnBoardingViewState())
    val viewState: StateFlow<OnBoardingViewState> = _viewState

    fun updateGlucoseUnits(units: GlucoseUnits) {
        viewModelScope.launch {
            repository.updateGlucoseUnits(units)
        }
        _viewState.update { _viewState.value.copy(units = units) }
    }

    fun addInsulin(insulin: Insulin) {
        viewModelScope.launch {
            repository.addInsulin(insulin.name, insulin.color.toString(), insulin.defaultDosage)
            _viewState.update { _viewState.value.copy(insulins = _viewState.value.insulins.plus(insulin)) }
        }
    }
}