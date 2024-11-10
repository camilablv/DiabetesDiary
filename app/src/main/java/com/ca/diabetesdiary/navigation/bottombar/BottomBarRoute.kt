package com.ca.diabetesdiary.navigation.bottombar

import kotlinx.serialization.Serializable

@Serializable
sealed class BottomBarRoute {
    @Serializable
    data object Home : BottomBarRoute()
    @Serializable
    data object Reminders : BottomBarRoute()
    @Serializable
    data object Records: BottomBarRoute()
}