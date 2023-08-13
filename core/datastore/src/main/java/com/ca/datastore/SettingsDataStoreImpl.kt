package com.ca.datastore

import androidx.datastore.core.DataStore
import com.ca.model.GlucoseUnits
import com.ca.model.Insulin
import com.ca.model.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class SettingsDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<SettingsProto>
) : SettingsDataStore {

    override suspend fun updateGlucoseUnits(units: GlucoseUnits): GlucoseUnits {
        return dataStore.updateData {
            it.toBuilder()
                .setUnit(SettingsProto.GlucoseUnit.valueOf(units.name))
                .build()
        }.glucoseUnit()
    }

    override suspend fun addInsulin(insulin: Insulin): List<Insulin> {
        return dataStore.updateData {
            it.toBuilder()
                .addInsulins(
                    SettingsProto.Insulin.newBuilder()
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

    override suspend fun updateInsulin(insulin: Insulin) {
        val insulins = dataStore.data.first().insulinsList
        val element = insulins.find { it.id == insulin.id }
        val index = insulins.indexOf(element)

        if (element == null) {
            addInsulin(insulin)
        } else {
            dataStore.updateData {
                it.toBuilder()
                    .setInsulins(
                        index,
                        SettingsProto.Insulin.newBuilder()
                            .setId(insulin.id)
                            .setName(insulin.name)
                            .setColor(insulin.color)
                            .setDefaultDosage(insulin.defaultDose)
                    )
                    .build()
            }
        }
    }

    override suspend fun insulins(): List<Insulin> {
        return dataStore.data.first().insulins()
    }

    override suspend fun setDarkMode(darkMode: Boolean) {
        dataStore.updateData {
            it.toBuilder()
                .setDarkMode(darkMode)
                .build()
        }
    }

    override suspend fun settings(): Flow<Settings> {
        isEmpty()
        return dataStore.data.asExternalModel()
    }

    override fun darkMode(): Flow<Boolean> = dataStore.data.map { it.darkMode }

    suspend fun isEmpty(): SettingsProto {
        return dataStore.data.first()
    }
}