package com.ca.settings.domain.repository

import com.ca.model.GlucoseUnits
import com.ca.model.Insulin

interface SettingsRepository {
    suspend fun updateGlucoseUnits(units: GlucoseUnits): GlucoseUnits
    suspend fun addInsulin(name: String, color: String, defaultDose: Int): List<Insulin>
    suspend fun updateInsulin(id: String, name: String, color: String, defaultDosage: Int)
    suspend fun deleteInsulin(id: String)
}