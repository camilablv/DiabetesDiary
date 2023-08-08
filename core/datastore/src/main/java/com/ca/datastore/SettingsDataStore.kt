package com.ca.datastore

import com.ca.model.ExternalSettings
import com.ca.model.GlucoseUnits
import com.ca.model.Insulin
import kotlinx.coroutines.flow.Flow

interface SettingsDataStore {
    suspend fun updateGlucoseUnits(units: GlucoseUnits): GlucoseUnits
    suspend fun addInsulin(insulin: Insulin): List<Insulin>
    suspend fun deleteInsulin(id: String): List<Insulin>
    suspend fun insulins(): List<Insulin>
    suspend fun setDarkMode(darkMode: Boolean)
    suspend fun settings(): Flow<ExternalSettings>
}