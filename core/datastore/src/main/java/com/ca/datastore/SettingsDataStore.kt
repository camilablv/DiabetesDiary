package com.ca.datastore

import com.ca.model.GlucoseUnits
import com.ca.model.Insulin
import com.ca.model.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsDataStore {
    suspend fun updateGlucoseUnits(units: GlucoseUnits): GlucoseUnits
    suspend fun addInsulin(insulin: Insulin): List<Insulin>
    suspend fun deleteInsulin(id: String): List<Insulin>
    suspend fun updateInsulin(insulin: Insulin)
    suspend fun insulins(): List<Insulin>
    suspend fun insulinsFlow(): Flow<List<Insulin>>
    suspend fun setDarkMode(darkMode: Boolean)
    suspend fun settings(): Flow<Settings>
    fun darkMode(): Flow<Boolean>
}