package com.ca.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ca.domain.repository.RemindersRepository
import com.ca.model.GlucoseUnits
import com.ca.domain.repository.SettingsRepository
import com.ca.model.Insulin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository,
    private val remindersRepository: RemindersRepository
) : ViewModel() {

    private var _viewState = MutableStateFlow(SettingsViewState())
    val viewState: StateFlow<SettingsViewState> = _viewState.asStateFlow()

    init {
        settings()
    }

    private fun settings() {
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

    fun editInsulin(id: String?, name: String, color: String, defaultDose: Int) {
        setRevealedInsulin(null)
        viewModelScope.launch {
            if (id == null) repository.addInsulin(name, color, defaultDose)
            else repository.updateInsulin(id, name, color, defaultDose)
        }
    }

    fun deleteInsulinWithReminders(insulin: Insulin?) {
        setRevealedInsulin(null)
        showDeleteInsulinDialog(false)
        if (insulin == null) return
        viewModelScope.launch {
            remindersRepository.insulinRemindersByInsulinId(insulin.id).forEach {
                remindersRepository.deleteInsulinReminder(it)
            }
            repository.deleteInsulin(insulin.id)
        }
    }

    fun deleteInsulin(insulin: Insulin?) {
        insulin?.let {
            viewModelScope.launch {
                val reminders = remindersRepository.insulinRemindersByInsulinId(it.id)
                if (reminders.isEmpty()) {
                    showDeleteInsulinDialog(false)
                    repository.deleteInsulin(it.id)
                }
                else showDeleteInsulinDialog(true)
            }
        }
    }

    fun showEditInsulinDialog(value: Boolean) {
        if (!value) setRevealedInsulin(null)
        _viewState.update { it.copy(showEditInsulinDialog = value) }
    }

    fun showDeleteInsulinDialog(value: Boolean) {
        if (!value) setRevealedInsulin(null)
        _viewState.update { it.copy(showDeleteInsulinDialog = value) }
    }

    fun setRevealedInsulin(insulin: Insulin?) {
        _viewState.update { it.copy(revealedInsulin = insulin) }
    }

    fun darkMode(darkMode: Boolean) {
        viewModelScope.launch {
            repository.darkMode(darkMode)
        }
    }
}