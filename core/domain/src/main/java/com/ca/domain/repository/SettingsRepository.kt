package com.ca.domain.repository

import com.ca.model.GlucoseUnits
import com.ca.model.Insulin
import com.ca.model.Settings
import kotlinx.coroutines.flow.Flow
import java.util.Locale

interface SettingsRepository {
    suspend fun updateGlucoseUnits(units: GlucoseUnits): GlucoseUnits?
    suspend fun addInsulin(name: String, color: String, defaultDose: Int): List<Insulin>?
    suspend fun updateInsulin(id: String, name: String, color: String, defaultDosage: Int)
    suspend fun deleteInsulin(id: String)
    suspend fun insulins(): List<Insulin>
    suspend fun insulinsFlow(): Flow<List<Insulin>>
    suspend fun settings(): Flow<Settings>
    suspend fun darkMode(darkMode: Boolean)
    fun defaultLocale(): Locale
    suspend fun setLocale(locale: Locale)
}