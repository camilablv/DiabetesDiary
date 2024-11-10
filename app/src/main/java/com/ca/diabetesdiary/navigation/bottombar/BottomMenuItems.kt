package com.ca.diabetesdiary.navigation.bottombar

import androidx.annotation.DrawableRes

sealed class BottomMenuItem(val route: BottomBarRoute, val title: String, @DrawableRes val icon: Int) {
    data object Home : BottomMenuItem(BottomBarRoute.Home, "Home", com.ca.designsystem.R.drawable.home)
    data object Reminder : BottomMenuItem(BottomBarRoute.Reminders, "Reminder", com.ca.designsystem.R.drawable.notifications)
    data object Records : BottomMenuItem(BottomBarRoute.Records, "Records", com.ca.designsystem.R.drawable.listbullet)
}

internal val bottomNavigationItems = listOf(
    BottomMenuItem.Home,
    BottomMenuItem.Reminder,
    BottomMenuItem.Records,
)