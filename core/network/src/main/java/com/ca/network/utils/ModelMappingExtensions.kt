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
        val dateTime = LocalDateTime.parse(it.takenAt.toString())
        InsulinRecord(
            cursor = it.cursor,
            id = it.id,
            insulin = it.insulin.insulin(),
            time = dateTime.toLocalTime(),
            date = dateTime.toLocalDate(),
            units = it.units,
            note = it.notes
        )

    }
}

fun RecordInsulinMutation.Data.record(insulin: Insulin): InsulinRecord {
    return with(record) {
        val dateTime = LocalDateTime.parse(takenAt.toString())
        InsulinRecord(
            cursor = insulin.id,
            id = id,
            insulin = insulin,
            time = dateTime.toLocalTime(),
            date = dateTime.toLocalDate(),
            units = units,
            note = notes
        )
    }
}

fun RecordGlucoseMutation.Data.record(): GlucoseRecord {
    return with(record) {
        val dateTime = LocalDateTime.parse(measuredAt.toString())
        GlucoseRecord(
            id = id,
            level = units,
            note = notes ?: "",
            time = dateTime.toLocalTime(),
            date = dateTime.toLocalDate(),
            measuringMark = MeasuringMark.valueOf(status.name)
        )
    }
}