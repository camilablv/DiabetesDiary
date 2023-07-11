package com.ca.home.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuItem(val route: String, val text: String, val icon: ImageVector) {
    object Home : BottomMenuItem(Route.Home.route, "Home", Icons.Filled.Home)
    object Reminder : BottomMenuItem(Route.Reminder.route, "Favorite", Icons.Filled.Star)
    object Settings : BottomMenuItem(Route.Settings.route, "Settings", Icons.Filled.Settings)
    object Records : BottomMenuItem(Route.Records.route, "Records", Icons.Filled.List)
}

internal val bottomNavigationItems = listOf(
    BottomMenuItem.Home,
    BottomMenuItem.Reminder,
    BottomMenuItem.Settings,
    BottomMenuItem.Records
)