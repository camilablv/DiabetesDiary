package com.ca.datastore

import androidx.datastore.core.DataStore
import com.ca.model.GlucoseUnits
import com.ca.model.Insulin
import javax.inject.Inject

class SettingsDataStore @Inject constructor(
    private val dataStore: DataStore<Settings>
) {

    suspend fun updateGlucoseUnits(units: com.ca.model.GlucoseUnits): com.ca.model.GlucoseUnits {
        val settings = dataStore.updateData {
            it.toBuilder()
                .setUnit(Settings.GlucoseUnit.valueOf(units.name))
                .build()
        }
        return settings.glucoseUnit()
    }

    suspend fun addInsulin(insulin: com.ca.model.Insulin): List<com.ca.model.Insulin> {
        val settings = dataStore.updateData {
            it.toBuilder()
                .addInsulins(
                    Settings.Insulin.newBuilder()
                        .setName(insulin.name)
                        .setColor(insulin.color)
                        .setDefaultDosage(insulin.defaultDosage)
                        .build()
                )
                .build()
        }
        return settings.insulins()
    }
}