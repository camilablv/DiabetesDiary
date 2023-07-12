package com.ca.network.utils

import com.ca.CreateInsulinMutation
import com.ca.InsulinRecordsQuery
import com.ca.UpdateGlucoseUnitMutation
import com.ca.model.GlucoseUnits
import com.ca.model.Insulin
import com.ca.model.InsulinRecord
import java.time.LocalDateTime

fun CreateInsulinMutation.Data.insulin(): Insulin {
    return insulin.let {
        Insulin(it.id, it.name, it.color, it.defaultDose!!)
    }
}

fun UpdateGlucoseUnitMutation.Data.unit(): GlucoseUnits {
    return GlucoseUnits.valueOf(settings.bloodGlucoseUnits?.name!!)
}

fun InsulinRecordsQuery.Insulin.insulin(): Insulin {
    return Insulin(
        id = id,
        name = name,
        color = color,
        defaultDose = defaultDose ?: 0
    )
}

fun InsulinRecordsQuery.Data.records(): List<InsulinRecord?>? {
    return records?.map { record ->
        record?.let {
            InsulinRecord(
                id = it.id,
                insulin = it.insulin.insulin(),
                dateTime = LocalDateTime.parse(it.takenAt.toString()),
                units = it.units,
                note = it.notes
            )
        }

    }
}