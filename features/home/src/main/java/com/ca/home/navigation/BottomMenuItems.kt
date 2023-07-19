package com.ca.home.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomMenuItem(Route.Home.route, "Home", Icons.Filled.Home)
    object Reminder : BottomMenuItem(Route.Reminder.route, "Reminder", Icons.Filled.Notifications)
    object Records : BottomMenuItem(Route.Records.route, "Records", Icons.Filled.List)
    object Settings : BottomMenuItem(Route.Settings.route, "Settings", Icons.Filled.Settings)
}

internal val bottomNavigationItems = listOf(
    BottomMenuItem.Home,
    BottomMenuItem.Reminder,
    BottomMenuItem.Records,
    BottomMenuItem.Settings
)