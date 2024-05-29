package com.ca.designsystem.components.glucosemeasuringmark

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.ca.designsystem.theme.*
import com.ca.model.MeasuringMark

sealed class MarkIcon(val mark: MeasuringMark, val backgroundColor: Color, @DrawableRes val icon: Int) {
    object General : MarkIcon(MeasuringMark.GENERAL, Grey100, com.ca.designsystem.R.drawable.person)
    object Fasting : MarkIcon(MeasuringMark.FASTING, Pink100, com.ca.designsystem.R.drawable.no_meals)
    object Premeal : MarkIcon(MeasuringMark.PRE_MEAL, Green100, com.ca.designsystem.R.drawable.lunch_dining)
    object Postmeal : MarkIcon(MeasuringMark.POST_MEAL, Orange100, com.ca.designsystem.R.drawable.restaurant)
    object BeforeSleep : MarkIcon(MeasuringMark.BEFORE_SLEEP, Blue100, com.ca.designsystem.R.drawable.bedtime)
}

internal val measuringMarkIcons = listOf(
    MarkIcon.General,
    MarkIcon.Fasting,
    MarkIcon.Premeal,
    MarkIcon.Postmeal,
    MarkIcon.BeforeSleep
)