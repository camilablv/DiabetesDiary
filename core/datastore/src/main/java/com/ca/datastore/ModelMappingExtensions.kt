package com.ca.datastore

import com.ca.model.GlucoseUnits
import com.ca.model.Insulin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

fun Settings.insulins(): List<Insulin> = insulinsList.map {
    Insulin(
        id = it.id,
        name = it.name,
        color = it.color,
        defaultDose = it.defaultDosage
    )
}

suspend fun Flow<Settings>.insulinsFlow(): Flow<List<Insulin>> {
    return flow {
        first().insulinsList.map {
            Insulin(
                id = it.id,
                name = it.name,
                color = it.color,
                defaultDose = it.defaultDosage
            )
        }
    }
}



fun Settings.glucoseUnit(): GlucoseUnits = GlucoseUnits.valueOf(unit.name)