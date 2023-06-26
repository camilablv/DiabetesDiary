package com.ca.settings.presentation

import com.ca.model.Insulin

data class SettingsViewState(
    val insulins: List<Insulin> = listOf()
)
