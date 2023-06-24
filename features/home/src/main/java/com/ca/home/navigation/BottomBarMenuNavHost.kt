package com.ca.home.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ca.home.presentation.HomeScreen
import com.ca.settings.presentation.SettingsScreen

@Composable
fun BottomBarMenuNavHost(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Route.Home.route) {
                HomeScreen(
                    navigateToRecordGlucoseScreen = { navController.navigate(Route.Glucose.List.route) },
                    navigateToRecordInsulinScreen = { navController.navigate(Route.Insulin.List.route) }
                )
            }
            composable(Route.Settings.route) {
                SettingsScreen()
            }
            glucoseGraph()
            insulinGraph()
        }
    }
}