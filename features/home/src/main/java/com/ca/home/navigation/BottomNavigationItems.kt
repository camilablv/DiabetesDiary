package com.ca.home.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItem(val route: String, val text: String, val icon: ImageVector) {
    object Home : BottomNavigationItem(Route.Home.route, "Home", Icons.Filled.Home)
    object Settings : BottomNavigationItem(Route.Settings.route, "Settings", Icons.Filled.Settings)
}

internal val bottomNavigationItems = listOf(
    BottomNavigationItem.Home,
    BottomNavigationItem.Settings,
)