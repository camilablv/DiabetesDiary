package com.ca.datastore

import androidx.datastore.core.DataStore
import com.ca.model.GlucoseUnits
import com.ca.model.Insulin
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class SettingsDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Settings>
) : SettingsDataStore {

    override suspend fun updateGlucoseUnits(units: GlucoseUnits): GlucoseUnits {
        return dataStore.updateData {
            it.toBuilder()
                .setUnit(Settings.GlucoseUnit.valueOf(units.name))
                .build()
        }.glucoseUnit()
    }

    override suspend fun addInsulin(insulin: Insulin): List<Insulin> {
        return dataStore.updateData {
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
        }.insulins()
    }

    override suspend fun deleteInsulin(id: String): List<Insulin> {
        val elementIndex = dataStore.data.first().insulinsList.let { list ->
            list.indexOf(list.find { insulin -> insulin.id == id })
        }
        return dataStore.updateData {
            it.toBuilder()
                .removeInsulins(elementIndex)
                .build()
        }.insulins()
    }

    override suspend fun insulins(): List<Insulin> {
        return dataStore.data.first().insulins()
    }
}