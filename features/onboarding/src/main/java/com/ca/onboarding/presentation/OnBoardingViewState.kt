package com.ca.onboarding.presentation

import com.ca.model.GlucoseUnits
import com.ca.model.Insulin

data class OnBoardingViewState(
    val units: com.ca.model.GlucoseUnits = com.ca.model.GlucoseUnits.MMOL_PER_L,
    val insulins: List<com.ca.model.Insulin> = listOf()
)
