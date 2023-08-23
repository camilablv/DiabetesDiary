package com.ca.domain.repository

import com.ca.domain.model.GlucoseUnits
import com.ca.domain.model.Insulin
import com.ca.domain.model.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun updateGlucoseUnits(units: GlucoseUnits): GlucoseUnits?
    suspend fun addInsulin(name: String, color: String, defaultDose: Int): List<Insulin>?
    suspend fun updateInsulin(id: String, name: String, color: String, defaultDosage: Int)
    suspend fun deleteInsulin(id: String)
    suspend fun insulins(): List<Insulin>
    suspend fun settings(): Flow<Settings>
    suspend fun darkMode(darkMode: Boolean)
}