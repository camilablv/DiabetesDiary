package com.ca.datastore

import com.ca.domain.model.GlucoseUnits
import com.ca.domain.model.Insulin
import com.ca.domain.model.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


fun Flow<SettingsProto>.asExternalModel(): Flow<Settings> = map {
    Settings(
        glucoseUnits = it.glucoseUnit(),
        insulins = it.insulins(),
        darkMode = it.darkMode
    )
}

fun SettingsProto.insulins(): List<Insulin> = insulinsList.map {
    Insulin(
        id = it.id,
        name = it.name,
        color = it.color,
        defaultDose = it.defaultDosage
    )
}

fun SettingsProto.glucoseUnit(): GlucoseUnits = GlucoseUnits.valueOf(unit.name)