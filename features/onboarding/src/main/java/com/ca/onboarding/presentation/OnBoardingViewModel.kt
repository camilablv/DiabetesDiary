package com.ca.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.domain.AddInsulinUseCase
import com.ca.domain.DeleteInsulinUseCase
import com.ca.domain.UpdateGlucoseUnitUseCase
import com.ca.model.GlucoseUnits
import com.ca.model.Insulin
import com.ca.settings.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val updateGlucoseUnitUseCase: UpdateGlucoseUnitUseCase,
    private val addInsulinUseCase: AddInsulinUseCase,
    private val deleteInsulinUseCase: DeleteInsulinUseCase,
    private val repository: SettingsRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(OnBoardingViewState())
    val viewState: StateFlow<OnBoardingViewState> = _viewState

    init {
        viewModelScope.launch {
            _viewState.update { it.copy(insulins = repository.insulins()) }
        }
    }

    fun updateGlucoseUnits(units: GlucoseUnits) {
        viewModelScope.launch {
            val unit = updateGlucoseUnitUseCase.invoke(units)
            _viewState.update { _viewState.value.copy(units = unit) }
        }
    }

    fun addInsulin(name: String, color: String, defaultDose: Int) {
        viewModelScope.launch {
            val insulins = addInsulinUseCase.invoke(name, color, defaultDose)
            insulins?.let { _viewState.update { _viewState.value.copy(insulins = insulins) } }
        }
    }

    fun deleteInsulin(id: String) {
        viewModelScope.launch {
            val insulins = deleteInsulinUseCase.invoke(id)
            _viewState.update { _viewState.value.copy(insulins = insulins) }
        }
    }
}