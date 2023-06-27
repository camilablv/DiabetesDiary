package com.ca.datastore

import com.ca.model.GlucoseUnits
import com.ca.model.Insulin

fun Settings.insulins(): List<Insulin> = insulinsList.map {
    Insulin(
        name = it.name,
        color = it.color,
        defaultDose = it.defaultDosage
    )
}

fun Settings.glucoseUnit(): GlucoseUnits = GlucoseUnits.valueOf(unit.name)