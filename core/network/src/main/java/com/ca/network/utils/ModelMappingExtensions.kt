package com.ca.network.utils

import com.ca.CreateInsulinMutation
import com.ca.UpdateGlucoseUnitMutation
import com.ca.model.GlucoseUnits
import com.ca.model.Insulin

fun CreateInsulinMutation.Data.insulin(): Insulin {
    return insulin.let {
        Insulin(it.id, it.name, it.color, it.defaultDose!!)
    }
}

fun UpdateGlucoseUnitMutation.Data.unit(): GlucoseUnits {
    return GlucoseUnits.valueOf(settings.bloodGlucoseUnits?.name!!)
}