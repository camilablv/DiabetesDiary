package com.ca.home.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomMenuItem(Route.Home.route, "Home", Icons.Filled.Home)
    object Settings : BottomMenuItem(Route.Settings.route, "Settings", Icons.Filled.Settings)
    object Records : BottomMenuItem(Route.Records.route, "Records", Icons.Filled.List)
    object ActionButton : BottomMenuItem("", "", Icons.Filled.Add)
}

internal val bottomNavigationItems = listOf(
    BottomMenuItem.Home,
    BottomMenuItem.Settings,
    BottomMenuItem.Records,
    BottomMenuItem.ActionButton
)