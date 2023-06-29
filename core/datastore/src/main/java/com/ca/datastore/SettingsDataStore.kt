package com.ca.datastore

import com.ca.model.GlucoseUnits
import com.ca.model.Insulin

interface SettingsDataStore {
    suspend fun updateGlucoseUnits(units: GlucoseUnits): GlucoseUnits
    suspend fun addInsulin(insulin: Insulin): List<Insulin>
    suspend fun deleteInsulin(id: String): List<Insulin>
    suspend fun insulins(): List<Insulin>
}