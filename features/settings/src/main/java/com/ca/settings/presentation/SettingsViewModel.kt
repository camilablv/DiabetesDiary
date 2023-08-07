package com.ca.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.model.GlucoseUnits
import com.ca.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
) : ViewModel() {

    private var _viewState = MutableStateFlow(SettingsViewState())
    val viewState: StateFlow<SettingsViewState> = _viewState

    init {
        viewModelScope.launch {
            _viewState.update { it.copy(insulins = repository.insulins()) }
        }
    }

    fun setGlucoseUnit(units: GlucoseUnits) {
        viewModelScope.launch {
            repository.updateGlucoseUnits(units)?.also { units ->
                _viewState.update { it.copy(glucoseUnits = units) }
            }
        }
    }

    fun addInsulin(name: String, color: String, defaultDose: Int) {
        viewModelScope.launch {
            repository.addInsulin(name, color, defaultDose)?.also { insulins ->
                _viewState.update { _viewState.value.copy(insulins = insulins)}
            }
        }
    }

    fun deleteInsulin(id: String) {
        viewModelScope.launch {
            repository.deleteInsulin(id).also { insulins ->
                _viewState.update { _viewState.value.copy(insulins = insulins) }
            }
        }
    }

    fun showAddInsulinDialog(value: Boolean) {
        _viewState.update { it.copy(showAddInsulinDialog = value) }
    }

    fun showEditInsulinDialog(value: Boolean) {
        _viewState.update { it.copy(showEditInsulinDialog = value) }
    }
}