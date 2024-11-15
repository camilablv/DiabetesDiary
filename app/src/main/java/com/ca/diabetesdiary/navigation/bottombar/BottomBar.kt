package com.ca.diabetesdiary.navigation.bottombar

import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ca.designsystem.theme.Theme

@Composable
fun BottomBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = Theme.colors.background,
        tonalElevation = BottomNavigationDefaults.Elevation
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavigationItems.forEach { screen ->
            BottomNavigationItem(
                selectedContentColor = Theme.colors.primary,
                unselectedContentColor = Theme.colors.onBackground,
                icon = { Icon(painter = painterResource(id = screen.icon), contentDescription = null) },
                label = { Text(text = screen.title) },
                selected = currentDestination?.hierarchy?.any { it == screen } == true,
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