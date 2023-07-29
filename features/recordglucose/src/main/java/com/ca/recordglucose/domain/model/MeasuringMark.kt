package com.ca.recordglucose.domain.model

import com.ca.model.Mark

enum class MeasuringMark(override val text: String, override val icon: Int) : Mark {
    GENERAL("General", com.ca.designsystem.R.drawable.person),
    FASTING("Fasting", com.ca.designsystem.R.drawable.no_meals),
    PRE_MEAL("Pre-meal", com.ca.designsystem.R.drawable.lunch_dining),
    POST_MEAL("Post-meal", com.ca.designsystem.R.drawable.restaurant),
    BEFORE_SLEEP("Before Sleep", com.ca.designsystem.R.drawable.bedtime)
}

internal val measuringMarks = listOf(
    MeasuringMark.GENERAL,
    MeasuringMark.FASTING,
    MeasuringMark.PRE_MEAL,
    MeasuringMark.POST_MEAL,
    MeasuringMark.BEFORE_SLEEP,
)