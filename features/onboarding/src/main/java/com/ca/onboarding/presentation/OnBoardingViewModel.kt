package com.ca.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.domain.model.GlucoseUnits
import com.ca.domain.model.Insulin
import com.ca.domain.repository.SettingsRepository
import com.ca.domain.usecase.UpdateGlucoseUnitUseCase
import com.ca.onboarding.domain.repository.OnBoardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val updateGlucoseUnitUseCase: UpdateGlucoseUnitUseCase,
    private val settingsRepository: SettingsRepository,
    private val onBoardingRepository: OnBoardingRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(OnBoardingViewState())
    val viewState: StateFlow<OnBoardingViewState> = _viewState

    init {
        viewModelScope.launch {
            settingsRepository.insulinsFlow().collect { list ->
                _viewState.update { it.copy(insulins = list) }
            }
        }
    }

    fun updateGlucoseUnits(units: GlucoseUnits) {
        viewModelScope.launch {
            updateGlucoseUnitUseCase.invoke(units)?.let { units ->
                _viewState.update { _viewState.value.copy(units = units) }
            }
        }
    }

    fun addInsulin(name: String, color: String, defaultDose: Int) {
        viewModelScope.launch {
            settingsRepository.addInsulin(name, color, defaultDose)
        }
    }

    fun deleteInsulin(insulin: Insulin) {
        viewModelScope.launch {
            settingsRepository.deleteInsulin(insulin.id)
        }
    }

    fun completeOnBoarding() {
        viewModelScope.launch {
            onBoardingRepository.onBoardingCompleted()
        }
    }

    fun setRevealedInsulin(insulin: Insulin?) {
        _viewState.update { it.copy(revealedInsulin = insulin) }
    }

    fun showEditInsulinDialog(value: Boolean) {
        if (!value) setRevealedInsulin(null)
        _viewState.update { it.copy(showEditInsulinDialog = value) }
    }

    fun editInsulin(id: String?, name: String, color: String, defaultDose: Int) {
        setRevealedInsulin(null)
        viewModelScope.launch {
            if (id == null) settingsRepository.addInsulin(name, color, defaultDose)
            else settingsRepository.updateInsulin(id, name, color, defaultDose)
        }
    }
}