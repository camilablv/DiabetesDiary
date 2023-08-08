package com.ca.settings.presentation

import com.ca.model.GlucoseUnits
import com.ca.model.Insulin

data class SettingsViewState(
    val glucoseUnits: GlucoseUnits = GlucoseUnits.MMOL_PER_L,
    val insulins: List<Insulin> = listOf(),
    val darkMode: Boolean = false,
    val showAddInsulinDialog: Boolean = false,
    val showEditInsulinDialog: Boolean = false,
)
