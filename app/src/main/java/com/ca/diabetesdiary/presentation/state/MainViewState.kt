package com.ca.diabetesdiary.presentation.state

import com.ca.diabetesdiary.navigation.MainGraph
import com.ca.model.TopLevelDestination

data class MainViewState(
    val startDestination: TopLevelDestination = MainGraph.Home,
    val shouldShowOnBoarding: Boolean = false,
    val darkMode: Boolean = false
)
