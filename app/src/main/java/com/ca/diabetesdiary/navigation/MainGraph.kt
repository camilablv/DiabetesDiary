package com.ca.diabetesdiary.navigation

import com.ca.model.TopLevelDestination
import kotlinx.serialization.Serializable

@Serializable
sealed class MainGraph : TopLevelDestination {
    @Serializable
    data object OnBoarding : MainGraph()
    @Serializable
    data object Home : MainGraph()
    @Serializable
    data object Settings : MainGraph()
}
