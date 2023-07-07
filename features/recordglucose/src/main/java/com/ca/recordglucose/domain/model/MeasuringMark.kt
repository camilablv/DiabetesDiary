package com.ca.recordglucose.domain.model

sealed class MeasuringMark(val time: String, val icon: Int) {
    object General : MeasuringMark("General", com.ca.designsystem.R.drawable.person)
    object Fasting : MeasuringMark("Fasting", com.ca.designsystem.R.drawable.no_meals)
    object PreMeal : MeasuringMark("Pre-meal", com.ca.designsystem.R.drawable.lunch_dining)
    object PostMeal : MeasuringMark("Post-meal", com.ca.designsystem.R.drawable.restaurant)
    object BeforeSleep : MeasuringMark("Before Sleep", com.ca.designsystem.R.drawable.bedtime)
}

internal val measuringMarks = listOf(
    MeasuringMark.General,
    MeasuringMark.Fasting,
    MeasuringMark.PreMeal,
    MeasuringMark.PostMeal,
    MeasuringMark.BeforeSleep,
)