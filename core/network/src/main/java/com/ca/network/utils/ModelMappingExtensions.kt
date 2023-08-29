package com.ca.network.utils

import com.ca.*
import com.ca.domain.model.*
import java.time.LocalDateTime

fun CreateInsulinMutation.Data.insulin(): Insulin {
    return insulin.let {
        Insulin(it.id, it.name, it.color, it.defaultDose!!)
    }
}

fun UpdateInsulinMutation.Data.insulin(): Insulin {
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

fun GlucoseRecordsQuery.Data.records(): List<GlucoseRecord> {
    return records.map {
        val dateTime = LocalDateTime.parse(it.measuredAt.toString())
        GlucoseRecord(
            cursor = it.cursor,
            id = it.id,
            level = it.units,
            note = it.notes,
            time = dateTime.toLocalTime(),
            date = dateTime.toLocalDate(),
            measuringMark = MeasuringMark.valueOf(it.status.name)
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
            cursor = id,
            id = id,
            level = units,
            note = notes ?: "",
            time = dateTime.toLocalTime(),
            date = dateTime.toLocalDate(),
            measuringMark = MeasuringMark.valueOf(status.name)
        )
    }
}

fun SettingsQuery.Insulin.insulin(): Insulin {
    return Insulin(
        id = id,
        name = name,
        color = color,
        defaultDose = defaultDose ?: 0
    )
}

fun SettingsQuery.Data.settings(): Settings {
    return with(settings) {
        Settings(
            glucoseUnits = GlucoseUnits.valueOf(settings.bloodGlucoseUnits?.name!!),
            insulins = if (insulins.isEmpty()) listOf() else insulins.map { it!!.insulin() },
            darkMode = false //TODO fix local model
        )
    }
}