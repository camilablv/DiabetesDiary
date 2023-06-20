package com.ca.onboarding.presentation

import com.ca.settings.domain.model.GlucoseUnits
import com.ca.settings.domain.model.Insulin

data class OnBoardingViewState(
    val units: GlucoseUnits = GlucoseUnits.MMOL_PER_L,
    val insulins: List<Insulin> = listOf()
)
