package com.ca.settings.presentation

import com.ca.model.GlucoseUnits
import com.ca.model.Insulin
import java.util.Locale


data class SettingsViewState(
    val glucoseUnits: GlucoseUnits = GlucoseUnits.MMOL_PER_L,
    val insulins: List<Insulin> = listOf(),
    val darkMode: Boolean = false,
    val currentLocale: Locale = Locale("en"),
    val showEditInsulinDialog: Boolean = false,
    val showDeleteInsulinDialog: Boolean = false,
    val showSetLocaleDialog: Boolean = false,
    val revealedInsulin: Insulin? = null
)
