package com.ca.designsystem.components.multifab

import androidx.annotation.DrawableRes
import com.ca.designsystem.R


sealed class RecordMenuItem(val id: Int, @DrawableRes val icon: Int, val label: String? = null) {
    object Glucose : RecordMenuItem(1, R.drawable.glucose, null)
    object Insulin : RecordMenuItem(2, R.drawable.vaccines, null)
}

val multiFabItems = listOf(
    RecordMenuItem.Insulin,
    RecordMenuItem.Glucose
)