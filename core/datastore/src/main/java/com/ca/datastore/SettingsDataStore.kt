package com.ca.datastore

import com.ca.domain.model.GlucoseUnits
import com.ca.domain.model.Insulin
import com.ca.domain.model.Settings
import kotlinx.coroutines.flow.Flow

interface SettingsDataStore {
    suspend fun updateGlucoseUnits(units: GlucoseUnits): GlucoseUnits
    suspend fun addInsulin(insulin: Insulin): List<Insulin>
    suspend fun deleteInsulin(id: String): List<Insulin>
    suspend fun updateInsulin(insulin: Insulin)
    suspend fun insulins(): List<Insulin>
    suspend fun setDarkMode(darkMode: Boolean)
    suspend fun settings(): Flow<Settings>
    fun darkMode(): Flow<Boolean>
}