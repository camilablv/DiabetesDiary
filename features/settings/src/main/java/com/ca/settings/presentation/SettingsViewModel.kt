package com.ca.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.model.GlucoseUnits
import com.ca.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
) : ViewModel() {

    private var _viewState = MutableStateFlow(SettingsViewState())
    val viewState: StateFlow<SettingsViewState> = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.settings().collect { settings ->
                _viewState.update {
                    it.copy(
                        glucoseUnits = settings.glucoseUnits,
                        insulins = settings.insulins,
                        darkMode = settings.darkMode
                    )
                }
            }

        }
    }

    fun setGlucoseUnit(units: GlucoseUnits) {
        viewModelScope.launch {
            repository.updateGlucoseUnits(units)
        }
    }

    fun addInsulin(name: String, color: String, defaultDose: Int) {
        viewModelScope.launch {
            repository.addInsulin(name, color, defaultDose)
        }
    }

    fun deleteInsulin(id: String) {
        viewModelScope.launch {
            repository.deleteInsulin(id)
        }
    }

    fun showAddInsulinDialog(value: Boolean) {
        _viewState.update { it.copy(showAddInsulinDialog = value) }
    }

    fun showEditInsulinDialog(value: Boolean) {
        _viewState.update { it.copy(showEditInsulinDialog = value) }
    }

    fun darkMode(darkMode: Boolean) {
        viewModelScope.launch {
            repository.darkMode(darkMode)
        }
    }
}