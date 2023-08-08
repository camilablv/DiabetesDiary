package com.ca.datastore

import com.ca.model.ExternalSettings
import com.ca.model.GlucoseUnits
import com.ca.model.Insulin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


fun Flow<Settings>.asExternalModel(): Flow<ExternalSettings> {
    return this.map {
        ExternalSettings(
            glucoseUnits = it.glucoseUnit(),
            insulins = it.insulins(),
            darkMode = it.darkMode
        )
    }
}

fun Settings.insulins(): List<Insulin> = insulinsList.map {
    Insulin(
        id = it.id,
        name = it.name,
        color = it.color,
        defaultDose = it.defaultDosage
    )
}

fun Settings.glucoseUnit(): GlucoseUnits = GlucoseUnits.valueOf(unit.name)