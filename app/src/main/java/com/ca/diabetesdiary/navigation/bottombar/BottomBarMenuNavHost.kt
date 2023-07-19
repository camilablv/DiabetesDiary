package com.ca.diabetesdiary.navigation.bottombar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ca.designsystem.components.MainTopBar
import com.ca.home.presentation.HomeScreen
import com.ca.records.presentation.RecordsScreen
import com.ca.reminders.recordinsulineminder.RecordInsulinReminderScreen
import com.ca.settings.presentation.SettingsScreen

@Composable
fun BottomBarMenuNavHost(
    navController: NavHostController = rememberNavController(),
    navigateToRecordGlucoseScreen: () -> Unit,
    navigateToRecordInsulinScreen: () -> Unit
) {
    Scaffold(
        topBar = { MainTopBar(title = "Diabetes Diary") },
        bottomBar = { BottomBar(navController = navController) },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomBarRoute.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomBarRoute.Home.route) {
                HomeScreen(
                    navigateToRecordGlucoseScreen = navigateToRecordGlucoseScreen,
                    navigateToRecordInsulinScreen = navigateToRecordInsulinScreen
                )
            }
            composable(BottomBarRoute.Settings.route) {
                SettingsScreen()
            }
            composable(BottomBarRoute.Records.route) {
                RecordsScreen()
            }
            composable(BottomBarRoute.Reminder.route) {
                RecordInsulinReminderScreen()
            }
        }
    }
}