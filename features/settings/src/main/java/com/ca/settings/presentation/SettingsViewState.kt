package com.ca.settings.presentation

import com.ca.domain.model.GlucoseUnits
import com.ca.domain.model.Insulin


data class SettingsViewState(
    val glucoseUnits: GlucoseUnits = GlucoseUnits.MMOL_PER_L,
    val insulins: List<Insulin> = listOf(),
    val darkMode: Boolean = false,
    val showEditInsulinDialog: Boolean = false,
    val showDeleteInsulinDialog: Boolean = false,
    val revealedInsulin: Insulin? = null
)
