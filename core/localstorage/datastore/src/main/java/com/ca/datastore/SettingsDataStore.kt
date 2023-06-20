package com.ca.datastore

import androidx.datastore.core.DataStore
import com.ca.data.model.GlucoseUnits
import com.ca.data.model.Insulin
import javax.inject.Inject

class SettingsDataStore @Inject constructor(
    private val dataStore: DataStore<Settings>
) {

    suspend fun updateGlucoseUnits(units: GlucoseUnits) {
        dataStore.updateData {
            it.toBuilder()
                .setUnit(Settings.GlucoseUnit.valueOf(units.name))
                .build()
        }
    }

    suspend fun addInsulin(insulin: Insulin) {
        dataStore.updateData {
            it.toBuilder()
                .addInsulins(
                    Settings.Insulin.newBuilder()
                        .setName(insulin.name)
                        .setDefaultDosage(insulin.defaultDosage)
                        .build()
                )
                .build()
        }
    }
}