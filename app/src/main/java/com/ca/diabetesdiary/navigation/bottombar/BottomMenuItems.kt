package com.ca.diabetesdiary.navigation.bottombar

import androidx.annotation.DrawableRes

sealed class BottomMenuItem(val route: String, val title: String, @DrawableRes val icon: Int) {
    object Home : BottomMenuItem(BottomBarRoute.Home.route, "Home", com.ca.designsystem.R.drawable.home)
    object Reminder : BottomMenuItem(BottomBarRoute.Reminder.route, "Reminder", com.ca.designsystem.R.drawable.notifications)
    object Records : BottomMenuItem(BottomBarRoute.Records.route, "Records", com.ca.designsystem.R.drawable.listbullet)
}

internal val bottomNavigationItems = listOf(
    BottomMenuItem.Home,
    BottomMenuItem.Reminder,
    BottomMenuItem.Records,
)