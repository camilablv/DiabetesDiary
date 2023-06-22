package com.ca.onboarding.presentation

import com.ca.model.GlucoseUnits
import com.ca.model.Insulin

data class OnBoardingViewState(
    val units: GlucoseUnits = GlucoseUnits.MMOL_PER_L,
    val insulins: List<Insulin> = listOf()
)
