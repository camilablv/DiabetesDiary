package com.ca.domain.model

data class Settings(
    val glucoseUnits: GlucoseUnits,
    val insulins: List<Insulin>,
    val darkMode: Boolean
)
