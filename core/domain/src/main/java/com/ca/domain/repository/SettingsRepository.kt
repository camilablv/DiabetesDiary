package com.ca.domain.repository

import com.ca.model.GlucoseUnits
import com.ca.model.Insulin
import com.ca.model.ExternalSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun updateGlucoseUnits(units: GlucoseUnits): GlucoseUnits?
    suspend fun addInsulin(name: String, color: String, defaultDose: Int): List<Insulin>?
    suspend fun updateInsulin(id: String, name: String, color: String, defaultDosage: Int)
    suspend fun deleteInsulin(id: String): List<Insulin>
    suspend fun insulins(): List<Insulin>
    suspend fun settings(): Flow<ExternalSettings>
    suspend fun darkMode(darkMode: Boolean)
}