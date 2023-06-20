package com.ca.data.model

enum class GlucoseUnits(val unit: String) {
    MG_PER_DL("mg/dL"),
    MMOL_PER_L("mmol/L");

    companion object {
        val units: List<GlucoseUnits> = listOf(MG_PER_DL, MMOL_PER_L)
    }
}