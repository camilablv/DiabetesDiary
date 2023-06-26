package com.ca.datastore

import com.ca.model.GlucoseUnits
import com.ca.model.Insulin

fun Settings.insulins(): List<com.ca.model.Insulin> = insulinsList.map {
    com.ca.model.Insulin(
        name = it.name,
        color = it.color,
        defaultDosage = it.defaultDosage
    )
}

fun Settings.glucoseUnit(): com.ca.model.GlucoseUnits = com.ca.model.GlucoseUnits.valueOf(unit.name)