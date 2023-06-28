package com.ca.datastore

import androidx.datastore.core.DataStore
import com.ca.model.GlucoseUnits
import com.ca.model.Insulin
import javax.inject.Inject

internal class SettingsDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Settings>
) : SettingsDataStore {

    override suspend fun updateGlucoseUnits(units: GlucoseUnits): GlucoseUnits {
        val settings = dataStore.updateData {
            it.toBuilder()
                .setUnit(Settings.GlucoseUnit.valueOf(units.name))
                .build()
        }
        return settings.glucoseUnit()
    }

    override suspend fun addInsulin(insulin: Insulin): List<Insulin> {
        val settings = dataStore.updateData {
            it.toBuilder()
                .addInsulins(
                    Settings.Insulin.newBuilder()
                        .setId(insulin.id)
                        .setName(insulin.name)
                        .setColor(insulin.color)
                        .setDefaultDosage(insulin.defaultDose)
                        .build()
                )
                .build()
        }
        return settings.insulins()
    }
}