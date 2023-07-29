package com.ca.network.utils

import com.ca.*
import com.ca.model.*
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

fun InsulinRecordsQuery.Data.records(): List<InsulinRecord> {
    return records.map {
        InsulinRecord(
            cursor = it.cursor,
            id = it.id,
            insulin = it.insulin.insulin(),
            dateTime = LocalDateTime.parse(it.takenAt.toString()),
            units = it.units,
            note = it.notes
        )

    }
}

fun RecordInsulinMutation.Data.record(insulin: Insulin): InsulinRecord {
    return with(record) {
        InsulinRecord(
            cursor = insulin.id,
            id = id,
            insulin = insulin,
            dateTime = LocalDateTime.parse(takenAt.toString()),
            units = units,
            note = notes
        )
    }
}

fun RecordGlucoseMutation.Data.record(): GlucoseRecord {
    return with(record) {
        GlucoseRecord(
            id = id,
            level = units,
            note = notes ?: "",
            dateTime = LocalDateTime.parse(measuredAt.toString()),
            measuringMark = status.name
        )
    }
}