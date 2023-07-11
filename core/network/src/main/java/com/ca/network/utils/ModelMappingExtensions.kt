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

fun InsulinRecordsQuery.Data.records(insulins: List<Insulin>): List<InsulinRecord?>? {
    return records?.map {
        it?.let {
            InsulinRecord(
                id = it.id,
                insulin = insulins.find { insulin -> insulin.id == it.insulinId }!!,
                dateTime = LocalDateTime.parse(it.takenAt.toString()),
                units = it.units,
                note = it.notes
            )
        }

    }
}