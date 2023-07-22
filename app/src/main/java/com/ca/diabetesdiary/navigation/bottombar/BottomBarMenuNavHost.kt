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
import com.ca.glucosereminder.navigation.navigateToGlucoseReminder
import com.ca.home.presentation.HomeScreen
import com.ca.insulinreminder.navigation.navigateToInsulinReminder
import com.ca.recordglucose.navigation.navigateToRecordGlucose
import com.ca.recordinsulin.navigation.navigateToRecordInsulin
import com.ca.records.presentation.RecordsScreen
import com.ca.reminders.presentation.RemindersScreen
import com.ca.settings.presentation.SettingsScreen

@Composable
fun BottomBarMenuNavHost(
    mainNavController: NavHostController
) {
    val bottomMenuNavHostController = rememberNavController()

    Scaffold(
        topBar = { MainTopBar(title = "Diabetes Diary") },
        bottomBar = { BottomBar(navController = bottomMenuNavHostController) },
    ) { innerPadding ->
        NavHost(
            navController = bottomMenuNavHostController,
            startDestination = BottomBarRoute.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomBarRoute.Home.route) {
                HomeScreen(
                    navigateToRecordGlucose = { mainNavController.navigateToRecordGlucose() },
                    navigateToRecordInsulin = { mainNavController.navigateToRecordInsulin() }
                )
            }
            composable(BottomBarRoute.Settings.route) {
                SettingsScreen()
            }
            composable(BottomBarRoute.Records.route) {
                RecordsScreen()
            }
            composable(BottomBarRoute.Reminder.route) {
                RemindersScreen(
                    navigateToAddInsulinReminder = { mainNavController.navigateToInsulinReminder() },
                    navigateToAddGlucoseReminder = { mainNavController.navigateToGlucoseReminder() }
                )
            }
        }
    }
}