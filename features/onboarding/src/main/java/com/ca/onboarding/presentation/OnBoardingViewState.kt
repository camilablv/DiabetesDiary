package com.ca.onboarding.presentation

import com.ca.settings.domain.model.GlucoseUnits

data class OnBoardingViewState(
    val units: GlucoseUnits = GlucoseUnits.MMOL_PER_L
)
