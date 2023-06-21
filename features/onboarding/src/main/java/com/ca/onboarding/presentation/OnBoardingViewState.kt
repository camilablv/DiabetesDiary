package com.ca.onboarding.presentation

import com.ca.domain.model.GlucoseUnits
import com.ca.domain.model.Insulin

data class OnBoardingViewState(
    val units: GlucoseUnits = GlucoseUnits.MMOL_PER_L,
    val insulins: List<Insulin> = listOf()
)
