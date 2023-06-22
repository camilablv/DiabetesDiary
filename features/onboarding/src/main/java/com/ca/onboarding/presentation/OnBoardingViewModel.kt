package com.ca.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.model.GlucoseUnits
import com.ca.model.Insulin
import com.ca.domain.AddInsulinUseCase
import com.ca.domain.UpdateGlucoseUnitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val updateGlucoseUnitUseCase: UpdateGlucoseUnitUseCase,
    private val addInsulinUseCase: AddInsulinUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(OnBoardingViewState())
    val viewState: StateFlow<OnBoardingViewState> = _viewState

    fun updateGlucoseUnits(units: GlucoseUnits) {
        viewModelScope.launch {
            val unit = updateGlucoseUnitUseCase.invoke(units)
            _viewState.update { _viewState.value.copy(units = unit) }
        }
    }

    fun addInsulin(insulin: Insulin) {
        viewModelScope.launch {
            val insulins = addInsulinUseCase.invoke(insulin)
            _viewState.update { _viewState.value.copy(insulins = insulins) }
        }
    }
}