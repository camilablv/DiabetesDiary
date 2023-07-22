package com.ca.diabetesdiary.navigation.bottombar

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ca.designsystem.theme.Theme

@Composable
fun BottomBar(navController: NavHostController) {
    BottomAppBar(
        backgroundColor = Theme.colors.background,
        cutoutShape = CircleShape
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavigationItems.forEach { screen ->
            BottomNavigationItem(
                selectedContentColor = Theme.colors.primary,
                unselectedContentColor = Theme.colors.onBackground,
                icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                label = { Text(text = screen.title) },
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