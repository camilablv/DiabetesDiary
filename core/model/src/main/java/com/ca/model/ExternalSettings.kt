package com.ca.model

data class ExternalSettings(
    val glucoseUnits: GlucoseUnits,
    val insulins: List<Insulin>,
    val darkMode: Boolean
)
