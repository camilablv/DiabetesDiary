package com.ca.datastore

import com.ca.data.model.GlucoseUnits
import com.ca.data.model.Insulin

fun Settings.insulins(): List<Insulin> {
    return insulinsList.map {
        Insulin(
            name = it.name,
            color = it.color,
            defaultDosage = it.defaultDosage
        )
    }
}

fun Settings.glucoseUnit(): GlucoseUnits {
    return GlucoseUnits.valueOf(unit.name)
}