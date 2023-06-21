package com.ca.datastore

import com.ca.domain.model.GlucoseUnits
import com.ca.domain.model.Insulin

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