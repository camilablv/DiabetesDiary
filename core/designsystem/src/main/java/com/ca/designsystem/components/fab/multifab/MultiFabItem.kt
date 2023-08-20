package com.ca.designsystem.components.fab.multifab

import androidx.annotation.DrawableRes
import com.ca.designsystem.R


sealed class MultiFabItem(@DrawableRes val icon: Int, val label: String? = null) {
    object RecordGlucose : MultiFabItem(R.drawable.glucose, "Glucose")
    object RecordInsulin : MultiFabItem(R.drawable.vaccines, "Insulin")
}

val multiFabItems = listOf(
    MultiFabItem.RecordInsulin,
    MultiFabItem.RecordGlucose
)