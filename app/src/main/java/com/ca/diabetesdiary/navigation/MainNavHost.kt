package com.ca.diabetesdiary.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ca.getstarted.navigation.getStartedNavGraph
import com.ca.home.navigation.BottomBarMenuNavHost

@Composable
fun MainNavHost(
    navHostController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(Route.Home.route) {
            BottomBarMenuNavHost()
        }

        getStartedNavGraph(
            navController = navHostController,
            route = Route.GetStarted.route,
            onComplete = {
                navHostController.navigate(Route.Home.route) {
                    popUpTo(Route.GetStarted.route) { inclusive = true }
                }
            },
            signInAnonymously = {  }
        )
    }
}