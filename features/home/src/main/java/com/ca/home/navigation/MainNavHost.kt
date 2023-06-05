package com.ca.home.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import com.ca.home.presentation.HomeScreen
import com.ca.recordglucose.presentation.RecordGlucoseScreen
import com.ca.recordinsulin.presentation.RecordInsulinScreen
import com.ca.settings.presentation.SettingsScreen

@Composable
fun MainNavHost(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavigationItems.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                        label = { Text(text = screen.text) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
                Button(onClick = { navController.navigate(Screen.Glucose.List.route) }) {
                    Text("Glucose")
                }
            }
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
            navigation(
                startDestination = Screen.Glucose.Record.route,
                route = Screen.Glucose.route
            ) {
                composable(Screen.Glucose.Record.route) {
                    RecordGlucoseScreen()
                }
                composable(Screen.Glucose.List.route) {
                    RecordGlucoseScreen()
                }
            }
            navigation(
                startDestination = Screen.Insulin.Record.route,
                route = Screen.Insulin.route
            ) {
                composable(Screen.Insulin.Record.route) {
                    RecordInsulinScreen()
                }
            }
        }
    }
}