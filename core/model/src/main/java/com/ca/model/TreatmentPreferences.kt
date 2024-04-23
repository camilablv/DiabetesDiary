package com.ca.model

data class TreatmentPreferences(
    val glucoseUnits: GlucoseUnits,
    val insulins: List<Insulin>
)