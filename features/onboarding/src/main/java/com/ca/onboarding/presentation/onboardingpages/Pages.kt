package com.ca.onboarding.presentation.onboardingpages

sealed class Page {
    object Welcome : Page()
    object AddInsulin : Page()
    object GlucoseUnits : Page()
    object Page4 : Page()
    object Page5 : Page()
}

val pages = listOf(
    Page.Welcome,
    Page.AddInsulin,
    Page.GlucoseUnits,
    Page.Page4,
    Page.Page5
)